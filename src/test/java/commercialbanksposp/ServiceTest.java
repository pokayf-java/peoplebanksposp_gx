package commercialbanksposp;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.poka.app.anno.base.service.impl.BankInfoService;
import com.poka.app.anno.base.service.impl.OrderInfoService;
import com.poka.app.anno.base.service.impl.ReserveDetailService;
import com.poka.app.anno.base.service.impl.ReserveMainService;
import com.poka.app.anno.enity.OrderInfo;
import com.poka.app.anno.enity.ReserveDetail;
import com.poka.app.enumtype.OrderType;
import com.poka.app.vo.AppointmentVo;

public class ServiceTest {

//	static ClassPathXmlApplicationContext ctx = null;
//
//	@BeforeClass
//	public static void before() {
//		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//	}
//
//	@Test
//	public void testPbOrderInfo() {
//
//		OrderInfoService orderInfoService = (OrderInfoService) ctx
//				.getBean("orderInfoService");
//		System.out.println(orderInfoService.getClass());
//		System.out.println(orderInfoService.getBaseDao().getClass());
//		System.out.println(orderInfoService.getAll().size());
//		ReserveMainService reserveMainService = (ReserveMainService) ctx
//				.getBean("reserveMainService");
//		System.out.println(reserveMainService.getBaseDao().getClass());
//
//		ReserveDetailService reserveDetailService = (ReserveDetailService) ctx
//				.getBean("reserveDetailService");
//		System.out.println(reserveDetailService.getBaseDao().getClass());
//		List<OrderInfo> orders = orderInfoService.getUnsendOrder(OrderType.APPOINTMENT);
//		
//		System.out.println("orders:"+orders.size());
//		
//		AppointmentVo vo = orderInfoService.getAppointmentVo("1");
//		System.out.println(vo.getReserveMain()==null?null:vo.getReserveMain().toString());
//		
//		for(ReserveDetail v:vo.getReserveDetails()){
//			System.out.println(v == null?null:v.toString());
//		}
//		
//		orderInfoService.saveAppointmentVo(null);
//		
//	}
//	
//	
//	@Test
//	public void testGetUrl(){
//		BankInfoService bankInfoService = (BankInfoService) ctx
//				.getBean("bankInfoService");
//		System.out.println(bankInfoService.getBankIp("0204"));
//	}
//
//	@AfterClass
//	public static void after() {
//		ctx.close();
//		ctx.destroy();
//	}
}
