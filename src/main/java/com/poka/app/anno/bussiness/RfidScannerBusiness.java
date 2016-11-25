package com.poka.app.anno.bussiness;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BagsCheckService;
import com.poka.app.anno.base.service.impl.PayBagService;
import com.poka.app.anno.base.service.impl.PayOrderService;
import com.poka.app.anno.enity.PayBag;
import com.poka.app.anno.enity.PayOrder;
import com.poka.app.anno.enity.StockBag;
 
/**
 * 扫描RFID进行处理...
 * @author Administrator
 *
 */
@Component
public class RfidScannerBusiness {
	Logger logger = Logger.getLogger(RfidScannerBusiness.class);
	private PayOrderService payOrderService;
	private BagsCheckService bagsCheckService;
	private PayBagService payBagService;

	@Autowired
	@Qualifier("payBagService")
	public void setPayBagService(PayBagService payBagService) {
		this.payBagService = payBagService;
	}

	@Autowired
	@Qualifier("bagsCheckService")
	public void setBagsCheckService(BagsCheckService bagsCheckService) {
		this.bagsCheckService = bagsCheckService;
	}

	@Autowired
	@Qualifier("payOrderService")
	public void setPayOrderService(PayOrderService payOrderService) {
		this.payOrderService = payOrderService;
	}

	/**
	 * 针对扫描的RFID标签进行处理
	 * 
	 * @param tagId
	 * @return
	 */
	public String doRfidScanner(String scannerInfo) {
		String[] tempIdArray = scannerInfo.split("-");
		// 标签Id
		String tagId = tempIdArray[0];
		// 机器Id
		String machId = tempIdArray[1];
		// 省份Id
		String provId = tempIdArray[2];
		// 单位Id
		String unitId = tempIdArray[3];

		String orderId = payOrderService.getOrderId(machId, unitId);
		if (orderId != "") {
			PayOrder payOrder = payOrderService.getOrderType(provId, unitId, orderId);
			Integer orderKind = payOrder.getOrderKind();
			String msg = payOrder.getMsg();
			String sourceId = payOrder.getSourceId();
			String fromUnitId = payOrder.getUnitId();
			if (tagId.length() == 20) {
				switch (orderKind) {
				case 1:
					//调拨出库
					return transferOut(orderId, tagId, provId, unitId);
				case 2:
					// 调拨入库
					return transferIn(orderId, tagId, provId, unitId);
				case 3:
					// 清分出库
					if (msg.equals("1")) {
						return clearingSend(orderId, tagId, provId, unitId);
					}
					// 清分实物接收
					if (msg.equals("2")) {
						return clearingEntityGet(orderId, tagId, provId, fromUnitId,false);
					}
					// 清分装袋复核
					if (msg.equals("3")) {
						return clearingBaggingRepeat(orderId, tagId, provId, unitId);
					}
				case 4:
					// 清分入库
					return clearingGet(orderId, tagId, provId, unitId, sourceId, false);
				case 5:
					// 复点出库
					if (msg.equals("1")) {
						return complexPointSend(orderId, tagId, provId, unitId);
					}
					// 复点实物接收
					if (msg.equals("2")) {
						return complexPointEntityGet(orderId, tagId, provId, fromUnitId);
					}
					// 复点装袋复核
					if (msg.equals("3")) {
						return complexPointBaggingRepeat(orderId, tagId, provId, unitId);
					}
				case 6:
					return complexPointGet(orderId, tagId, provId, unitId, sourceId);
				case 7:
					// 销毁出库
					if (msg.equals("1")) {
						return destroySend(orderId, tagId, provId, unitId);
					}
					// 销毁实物接收
					if (msg.equals("2")) {
						return destroyEntityGet(orderId, tagId, provId, fromUnitId);
					}
				case 11:
					// 交款
					return bankSend(orderId, tagId, provId, unitId);
				case 12:
					// 取款
					return bankGet(orderId, tagId, provId);
				default:
					return "UnknownError";
				}
			} else {
				return "IsNotBagCode";
			}
		} else {
			// 没有正在处理的订单
			return "NoOrderIsHandle";
		}
	}

	/**
	 * 清分入库
	 * 
	 * 
	 * @return
	 */
	private String clearingGet(String orderId, String tagId, String provId, String unitId, String sourceId, boolean oldOrderIdFlag) {

		boolean bagsCheckFlag = payOrderService.getBagsCheckFlag(orderId, tagId, provId, unitId, false);
		if (bagsCheckFlag) {
			return "BagCodeAlreadyChecked";
		} else {
			PayBag payBag = payOrderService.getCurrencyIdKindForBags(tagId, provId, sourceId, unitId, true, false);
			if (null != payBag) {

				String currencyId = payBag.getCurrencyId();
				Integer currencyKind = payBag.getCurrencyKind();
				if(oldOrderIdFlag){
					orderId = payBag.getOrderId();
				}
				
				boolean orderDetailFlag = payOrderService.getOrderDetailFlag(orderId, currencyId, currencyKind, provId,
						null, false);
				if (!orderDetailFlag) {
					return "BagCurrencyIdORCurrencyKindIsNotInOrderDetail";
				} else {

					boolean insertBagsCheckFlag = bagsCheckService.insertBagsCheckFlag(orderId, tagId, currencyId,
							currencyKind, provId, unitId, false, null);
					if (insertBagsCheckFlag) {
						return "Success";
					} else {
						return "UnknownError";
					}
				}

			} else {
				return "BagIsNotInStock";
			}
		}
	}

	/**
	 * 清分出库
	 * 
	 * 
	 * 
	 * @return 处理结果
	 */
	private String clearingSend(String orderId, String tagId, String provId, String unitId) {

		boolean bagsCheckFlag = payOrderService.getBagsCheckFlag(orderId, tagId, provId, unitId, true);
		if (bagsCheckFlag) {
			return "BagCodeAlreadyChecked";
		} else {
			StockBag stockBag = payOrderService.getCurrencyIdKindForStock(tagId, provId, unitId);
			if (null != stockBag) {
				Integer currencyKind = stockBag.getCurrencyKind();
				String currencyId = stockBag.getCurrencyId();
				boolean orderDetailFlag = payOrderService.getOrderDetailFlag(orderId, currencyId, currencyKind, provId,
						unitId, true);

				if (!orderDetailFlag) {
					return "BagCurrencyIdORCurrencyKindIsNotInOrderDetail";
				} else {
					boolean insertBagsCheckFlag = bagsCheckService.insertBagsCheckFlag(orderId, tagId, currencyId,
							currencyKind, provId, unitId, false, null);
					if (insertBagsCheckFlag) {
						return "Success";
					} else {
						return "UnknownError";
					}
				}
			} else {
				return "BagIsNotInStock";
			}
		}
	}

	/**
	 * 清分实物接收
	 * 
	 * 
	 * 
	 * @return 处理结果
	 */
	private String clearingEntityGet(String orderId, String tagId, String provId, String unitId, boolean unitFlagForBags) {
		boolean bagsCheckFlag = payOrderService.getBagsCheckFlag(orderId, tagId, provId, null, false);
		if (bagsCheckFlag) {
			return "BagCodeAlreadyChecked";
		} else {
			PayBag payBag = null;
			if(unitFlagForBags){
				payBag = payOrderService.getCurrencyIdKindForBags(tagId, provId, orderId, unitId, false, unitFlagForBags);
			}else{
				payBag = payOrderService.getCurrencyIdKindForBags(tagId, provId, orderId, null, false, unitFlagForBags);
			}
			if (null != payBag) {
				String currencyId = payBag.getCurrencyId();
				Integer currencyKind = payBag.getCurrencyKind();

				boolean orderDetailFlag = payOrderService.getOrderDetailFlag(orderId, currencyId, currencyKind, provId,
						null, false);
				if (!orderDetailFlag) {
					return "BagCurrencyIdORCurrencyKindIsNotInOrderDetail";
				} else {

					boolean insertBagsCheckFlag = bagsCheckService.insertBagsCheckFlag(orderId, tagId, currencyId,
							currencyKind, provId, unitId, false, null);
					if (insertBagsCheckFlag) {
						return "Success";
					} else {
						return "UnknownError";
					}
				}
			} else {
				return "BagIsNotInStock";
			}
		}
	}

	
	/**
	 * 清分装袋复核
	 * @param orderId
	 * @param tagId
	 * @param provId
	 * @param unitId
	 * @return
	 */
	public String clearingBaggingRepeat(String orderId, String tagId, String provId, String unitId){
		return clearingEntityGet(orderId, tagId, provId, unitId, true);
	}
	
	/**
	 * 复点出库
	 * 
	 * @return
	 */
	public String complexPointSend(String orderId, String tagId, String provId, String unitId) {
		return clearingSend(orderId, tagId, provId, unitId);
	}
	
	/**
	 * 复点入库
	 * @param orderId
	 * @param tagId
	 * @param provId
	 * @param unitId
	 * @return
	 */
	public String complexPointGet(String orderId, String tagId, String provId, String unitId, String sourceId){
		return clearingGet(orderId, tagId, provId, unitId, sourceId, true);
	}

	/**
	 * 复点实物接收
	 * 
	 * @param orderId
	 * @param tagId
	 * @param provId
	 * @param unitId
	 * @return
	 */
	public String complexPointEntityGet(String orderId, String tagId, String provId, String unitId) {
		return clearingEntityGet(orderId, tagId, provId, unitId, true);
	}
	
	/**
	 * 复点装袋复核
	 * @param orderId
	 * @param tagId
	 * @param provId
	 * @param unitId
	 * @return
	 */
	public String complexPointBaggingRepeat(String orderId, String tagId, String provId, String unitId){
		return clearingEntityGet(orderId, tagId, provId, unitId, true);
	}

	/**
	 * 销毁出库
	 * 
	 * @return 处理结果
	 */
	private String destroySend(String orderId, String tagId, String provId, String unitId) {
		return clearingSend(orderId, tagId, provId, unitId);
	}

	/**
	 * 销毁实物接收
	 * 
	 * @param orderId
	 * @param tagId
	 * @param provId
	 * @param unitId
	 * @return
	 */
	public String destroyEntityGet(String orderId, String tagId, String provId, String unitId) {
		return clearingEntityGet(orderId, tagId, provId, unitId,false);
	}

	/**
	 * 商行取款的方法
	 * 
	 * 
	 * 
	 * @return 处理结果
	 */
	private String bankSend(String orderId, String tagId, String provId, String unitId) {
		return clearingSend(orderId, tagId, provId, unitId);
	}

	/**
	 * 商行交款的方法
	 * 
	 * @return 处理结果
	 * 
	 * 
	 */
	private String bankGet(String orderId, String tagId, String provId) {
		PayBag payBag = payBagService.selectBagsInfo(orderId, tagId);
		if (null != payBag) {

			String currencyId = payBag.getCurrencyId();
			Integer currencyKind = payBag.getCurrencyKind();

			boolean orderDetailFlag = payOrderService.getOrderDetailFlag(orderId, currencyId, currencyKind, provId,
					null, false);
			if (!orderDetailFlag) {
				return "BagCurrencyIdORCurrencyKindIsNotInOrderDetail";
			} else {
				boolean updateFlag = payBagService.updateBags(orderId, tagId);
				if (updateFlag) {
					return "Success";
				} else {
					return "BagCodeAlreadyChecked";
				}
			}
		} else {
			return "BagCodeIsNotInTheOrder";
		}
	}
	
	/**
	 * 调拨出库
	 * @param orderId
	 * @param tagId
	 * @param provId
	 * @param unitId
	 * @return
	 */
	public String transferOut(String orderId, String tagId, String provId, String unitId){
		return clearingSend(orderId, tagId, provId, unitId);
	}
	
	/**
	 * 调拨入库
	 * @param orderId
	 * @param tagId
	 * @param provId
	 * @param unitId
	 * @return
	 */
	public String transferIn(String orderId, String tagId, String provId, String unitId){
		return clearingEntityGet(orderId, tagId, provId, unitId, false);
	}
}
