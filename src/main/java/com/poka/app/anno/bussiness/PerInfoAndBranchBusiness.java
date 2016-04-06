package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.poka.app.anno.base.service.impl.PerInfoService;
import com.poka.app.anno.base.service.impl.BranchInfoService;
import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.BranchInfoPK;
import com.poka.app.anno.enity.PerInfo;

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

	public boolean updatePerInfo(String sData) {
		Gson gson = new Gson();
		List<PerInfo> list = gson.fromJson(sData, new TypeToken<List<PerInfo>>(){}.getType());
		
		if (list.size() <= 0) {
			return false;
		}
		for (PerInfo per : list) {
			PerInfo tem = perInfoService.getPerinfo(per.getPercode());
			
			if(tem == null){
				perInfoService.save(per);
				continue;
			}
			tem.setAcctaddr(per.getAcctaddr());
			tem.setAgencyno(per.getAgencyno());
			tem.setBankno(per.getBankno());
			tem.setBrand(per.getBrand());
			tem.setModel(per.getModel());
			tem.setPertype(per.getPertype());
			tem.setQuality(per.getQuality());
			tem.setStatus(per.getStatus());
			perInfoService.save(tem);
		}
		perInfoService.flush();
		logger.info("机具表数据同步成功！("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")");
		return true;
	}
	
	public boolean updateBranchInfo(String sData){
		Gson gson = new Gson();
		List<BranchInfo> list = gson.fromJson(sData, new TypeToken<List<BranchInfo>>(){}.getType());
//		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		logger.info("sData:"+sData);
//		logger.info("*************************************************************************");
//		logger.info("breanchinfo :"+list.size());
//		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (list.size() <= 0) {
			return false;
		}
		for (BranchInfo branch : list) {
			BranchInfoPK pk = new BranchInfoPK();
			pk.setAgencyno(branch.getAgencyno());
			pk.setBankno(branch.getBankno());
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
		return true;
	}
}
