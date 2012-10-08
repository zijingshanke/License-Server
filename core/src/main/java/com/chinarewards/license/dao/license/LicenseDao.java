package com.chinarewards.license.dao.license;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.license.common.BaseDao;
import com.chinarewards.license.domain.license.License;
import com.chinarewards.license.model.license.search.LicenseListVo;
import com.chinarewards.license.util.StringUtil;

public class LicenseDao extends BaseDao<License> {
	@SuppressWarnings("unchecked")
	public List<License> licenseList(LicenseListVo licenseVo) {
		List<License> result = new ArrayList<License>();

		Query query = getFetchLicenseQuery(SEARCH, licenseVo);

		result = query.getResultList();
		logger.debug("finished by fetchLicense method. result.size:{}",
				result.size());
		return result;
	}

	public int countLicense(LicenseListVo licenseVo) {
		logger.debug(" Process in countLicense method, parameter : {}",
				licenseVo.toString());
		int count = 0;
		Query query = getFetchLicenseQuery(COUNT, licenseVo);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by license method, result count : {}", count);
		return count;
	}

	private Query getFetchLicenseQuery(String type, LicenseListVo criteria) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT g FROM License g WHERE 1 = 1 and  g.deleted= :deleted");
			param.put("deleted", false);
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(g) FROM License g WHERE 1 = 1 and  g.deleted= :deleted");
			param.put("deleted", false);
		}
		if (criteria.getStatus()!=null) {
			eql.append(" AND UPPER(g.status) = :status ");
			param.put("status", criteria.getStatus());
		}
	
		if (!StringUtil.isEmptyString(criteria.getCorporationName())) {
			eql.append(" AND g.corporationName LIKE :corporationname ");
			param.put("corporationname", "%" + criteria.getCorporationName().trim().toUpperCase()
					+ "%");
		}
	
		if (SEARCH.equals(type)) {
		if (criteria.getSortingDetail() != null) {
			eql.append(" ORDER BY g."
					+ criteria.getSortingDetail().getSort() + " "
					+ criteria.getSortingDetail().getDirection());
		}
		}
		System.out.println("EQL : " + eql);
		Query query = getEm().createQuery(eql.toString());
		if (SEARCH.equals(type)) {
			if (criteria.getPaginationDetail() != null) {
				int start = criteria.getPaginationDetail().getStart();
				int limit = criteria.getPaginationDetail().getLimit();
				logger.debug(" paginationDetail - start:{}, limit:{}",
						new Object[] { start, limit });

				query.setFirstResult(start);
				query.setMaxResults(limit);
			}
		}
		if (param.size() > 0) {
			Set<String> key = param.keySet();
			for (String s : key) {
				query.setParameter(s, param.get(s));
			}
		}

		return query;
	}
}
