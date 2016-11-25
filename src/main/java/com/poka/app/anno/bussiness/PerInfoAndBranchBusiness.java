package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BranchInfoService;
import com.poka.app.anno.base.service.impl.PerInfoService;
import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.BranchInfoPK;
import com.poka.app.anno.enity.PerInfo;
import com.poka.app.anno.enity.PerInfoPK;

@Component
public class PerInfoAndBranchBusiness {
	Logger logger = Logger.getLogger(PerInfoAndBranchBusiness.class);
	private PerInfoService perInfoService;
	private BranchInfoService branchInfoService;
	
	
	@Autowired 
	@Qualifier("branchInfoService")
	public void setBranchInfoService(BranchInfoService branchInfoService) {
		this.branchInfoService = branchInfoService;
	}

	@Autowired 
	@Qualifier("perInfoService")
	public void setPerInfoService(PerInfoService perInfoService) {
		this.perInfoService = perInfoService;
	}

	public boolean updatePerInfo(List<PerInfo> perInfoList) {
		Integer perInfoLisSize = perInfoList.size();
		String bankNo = null;
		if (perInfoLisSize <= 0) {
			return false;
		}
		for (PerInfo per : perInfoList) {
			PerInfoPK perInfoPK = new PerInfoPK();
			perInfoPK.setPercode(per.getPercode());
			perInfoPK.setBankno(per.getBankno());
			perInfoPK.setAgencyno(per.getAgencyno());
			PerInfo tem = perInfoService.getPerinfo(perInfoPK);
			bankNo = per.getBankno();
			if(tem == null){
				perInfoService.save(per);
				continue;
			}
			String perType = per.getPertype();
			String brand = per.getBrand();
			String model = per.getModel();
			String acctAddr = per.getAcctaddr();
			String quality = per.getQuality();
			Character status = per.getStatus();
			String useStatus = per.getUseStatus();
			if (perType.equals("99") || perType == "99") {

			} else {
				tem.setPercode(per.getPercode());
				tem.setAgencyno(per.getAgencyno());
				tem.setBankno(bankNo);

				if (null != brand && !brand.equals("")) {
					tem.setBrand(brand);
				}
				if (null != perType && !perType.equals("")) {
					tem.setPertype(perType);
				}
				if (null != model&& !model.equals("")) {
					tem.setModel(model);
				}
				if (null != acctAddr&& !acctAddr.equals("")) {
					tem.setAcctaddr(acctAddr);
				}
				if (null != quality&& !quality.equals("")) {
					tem.setQuality(quality);
				}
				if (null != status&& !status.equals("")) {
					tem.setStatus(status);
				}
				if (null != useStatus&& !useStatus.equals("")) {
					tem.setUseStatus(useStatus);
				}
				perInfoService.save(tem);
			}
		}
		perInfoService.flush();
		logger.info("机具表数据同步成功！("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")");
		logger.info("银行号："+bankNo+",共计" + perInfoLisSize + "条.");
		return true;
	}
	
	public boolean updateBranchInfo(List<BranchInfo> branchInfoList){
		Integer branchInfoListSize = branchInfoList.size();
		String bankNo = null;
		if (branchInfoListSize <= 0) {
			return false;
		}
		for (BranchInfo branch : branchInfoList) {
			bankNo = branch.getBankno();
			BranchInfoPK pk = new BranchInfoPK();
			pk.setAgencyno(branch.getAgencyno());
			pk.setBankno(bankNo);
			BranchInfo tem = branchInfoService.getBranchInfo(pk);
			if(tem == null){
				branchInfoService.save(branch);
				continue;
			}
			tem.setAddress(branch.getAddress());
			tem.setBranchname(branch.getBranchname());
			tem.setRemark(branch.getRemark());
			tem.setTelphone(branch.getTelphone());
			branchInfoService.save(tem);
		}
		branchInfoService.flush();
		logger.info("网点表数据同步成功！("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")");
		logger.info("银行号："+bankNo+",共计" + branchInfoListSize + "条.");
		return true;
	}
}
