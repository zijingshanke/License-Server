//package com.chinarewards.license.service.common;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import com.chinarewards.license.dao.user.UserDao;
//import com.chinarewards.license.domain.user.SysUser;
//import com.chinarewards.license.model.user.UserContext;
//import com.chinarewards.license.model.user.UserRole;
//import com.google.inject.persist.PersistService;
//import com.google.inject.persist.UnitOfWork;
//
//public abstract class JPATestCase extends GuiceTestCase {
//
//	protected EntityManager em;
//	protected UnitOfWork uom;
//	protected static PersistService persistService;
//
//	public JPATestCase() {
//		super();
//	}
//
//	public EntityManager getEm() {
//		return em;
//	}
//
//	public void begin() {
//		em.getTransaction().begin();
//	}
//
//	public void commit() {
//		em.getTransaction().commit();
//	}
//
//	public void rollback() {
//		em.getTransaction().rollback();
//	}
//
//	protected void setUp() throws Exception {
//		super.setUp();
//		if (persistService == null) {
//			persistService = injector.getInstance(PersistService.class);
//			persistService.start();
//		}
//		// uom = injector.getInstance(UnitOfWork.class);
//		// uom.begin();
//		this.em = injector.getInstance(EntityManager.class);
//		logger.debug("Begin a Transaction");
//		em.getTransaction().begin();
//	}
//
//	protected void tearDown() throws Exception {
//		if (em.getTransaction().isActive()) {
//			rollback();
//			logger.debug("Shutdown testcase, rolled-back transaction!");
//		}
//		// uom.end();
//		super.tearDown();
//	}
//
//	protected void initSysParams() {
//		// system parameter configuration.
//		// injector.getInstance(SysConfigureImpl.class).configure();
//	}
//
//	protected void persist(Object o) {
//		em.persist(o);
//	}
//
//
//
//	protected UserContext getUserContext(String corporationId) {
//	
//		// corporation.setCorporationId(enterpriseId);
//		// corporation.setName("initCorportaion");
//		// CorporationDao corporationDao = this.injector
//		// .getInstance(CorporationDao.class);
//		// corporationDao.save(corporation);
//
//		// a user record is required
//		SysUser caller = new SysUser();
//		caller.setUserName("aa80064a4b4");
//		caller.setPassword("12345680064a4b480064a4b4");
//		UserDao dao = this.injector.getInstance(UserDao.class);
//		dao.save(caller);
//
//		// prepare user context
//		List<UserRole> roles = new ArrayList<UserRole>();
//		roles.add(UserRole.CORP_ADMIN);
//
//		UserContext ctx = new UserContext();
//		ctx.setUserId(caller.getId());
//		ctx.setLoginName(caller.getUserName());
//		ctx.setUserRoles(roles.toArray(new UserRole[roles.size()]));
//
//		return ctx;
//	}
//
//}
