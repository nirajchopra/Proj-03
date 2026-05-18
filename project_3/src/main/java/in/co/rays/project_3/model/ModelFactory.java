package in.co.rays.project_3.model;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * ModelFactory decides which model implementation run
 * 
 * @author Anand Choudhary
 */
public final class ModelFactory {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.project_3.bundle.system");
	private static final String DATABASE = rb.getString("DATABASE");
	private static ModelFactory mFactory = null;
	private static HashMap modelCache = new HashMap();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {
		if (mFactory == null) {
			mFactory = new ModelFactory();
		}
		return mFactory;
	}

	public ProductModelInt getProductModel() {
		ProductModelInt productModel = (ProductModelInt) modelCache.get("productModel");
		if (productModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				productModel = new ProductModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				productModel = new ProductModelHibImp();
			}
			modelCache.put("productModel", productModel);
		}
		return productModel;
	}

	public MarksheetModelInt getMarksheetModel() {
		MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache.get("marksheetModel");
		if (marksheetModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				marksheetModel = new MarksheetModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				marksheetModel = new MarksheetModelJDBCImpl();
			}
			modelCache.put("marksheetModel", marksheetModel);
		}
		return marksheetModel;
	}

	public CollegeModelInt getCollegeModel() {
		CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");
		if (collegeModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				collegeModel = new CollegeModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				collegeModel = new CollegeModelJDBCImpl();
			}
			modelCache.put("collegeModel", collegeModel);
		}
		return collegeModel;
	}

	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
		if (roleModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				roleModel = new RoleModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				roleModel = new RoleModelJDBCImpl();
			}
			modelCache.put("roleModel", roleModel);
		}
		return roleModel;
	}

	public UserModelInt getUserModel() {

		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");
		if (userModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				userModel = new UserModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				userModel = new UserModelJDBCImpl();
			}
			modelCache.put("userModel", userModel);
		}

		return userModel;
	}

	public StudentModelInt getStudentModel() {
		StudentModelInt studentModel = (StudentModelInt) modelCache.get("studentModel");
		if (studentModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				studentModel = new StudentModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				studentModel = new StudentModelJDBCImpl();
			}
			modelCache.put("studentModel", studentModel);
		}

		return studentModel;
	}

	public CourseModelInt getCourseModel() {
		CourseModelInt courseModel = (CourseModelInt) modelCache.get("courseModel");
		if (courseModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				courseModel = new CourseModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				courseModel = new CourseModelJDBCImpl();
			}
			modelCache.put("courseModel", courseModel);
		}

		return courseModel;
	}

	public TimetableModelInt getTimetableModel() {

		TimetableModelInt timetableModel = (TimetableModelInt) modelCache.get("timetableModel");

		if (timetableModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				timetableModel = new TimetableModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				timetableModel = new TimetableModelJDBCImpl();
			}
			modelCache.put("timetableModel", timetableModel);
		}

		return timetableModel;
	}

	public SubjectModelInt getSubjectModel() {
		SubjectModelInt subjectModel = (SubjectModelInt) modelCache.get("subjectModel");
		if (subjectModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				subjectModel = new SubjectModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				subjectModel = new SubjectModelJDBCImpl();
			}
			modelCache.put("subjectModel", subjectModel);
		}

		return subjectModel;
	}

	public FacultyModelInt getFacultyModel() {
		FacultyModelInt facultyModel = (FacultyModelInt) modelCache.get("facultyModel");
		if (facultyModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				facultyModel = new FacultyModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				facultyModel = new FacultyModelJDBCImpl();
			}
			modelCache.put("facultyModel", facultyModel);
		}

		return facultyModel;
	}

	public ProfileModelInt getProfileModel() {
		ProfileModelInt profileModel = (ProfileModelInt) modelCache.get("profileModel");
		if (profileModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				profileModel = new ProfileModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				profileModel = new ProfileModelHibImpl();
			}
			modelCache.put("profileModel", profileModel);
		}

		return profileModel;
	}

	public InventoryModelInt getInventoryModel() {

		InventoryModelInt inventoryModel = (InventoryModelInt) modelCache.get("inventoryModel");

		if (inventoryModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				inventoryModel = new InventoryModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				inventoryModel = new InventoryModelHibImp();
			}

			modelCache.put("inventoryModel", inventoryModel);
		}

		return inventoryModel;
	}

	public SessionModelInt getSessionModel() {

		SessionModelInt sessionModel = (SessionModelInt) modelCache.get("sessionModel");

		if (sessionModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				sessionModel = new SessionModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				sessionModel = new SessionModelHibImp(); // change if JDBC impl created
			}

			modelCache.put("sessionModel", sessionModel);
		}

		return sessionModel;
	}

	public LanguageModelInt getLanguageModel() {

		LanguageModelInt languageModel = (LanguageModelInt) modelCache.get("languageModel");

		if (languageModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				languageModel = new LanguageModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				languageModel = new LanguageModelHibImp(); // change if JDBC impl created
			}

			modelCache.put("languageModel", languageModel);
		}

		return languageModel;
	}

	public AnnouncementModelInt getAnnouncementModel() {

		AnnouncementModelInt announcementModel = (AnnouncementModelInt) modelCache.get("announcementModel");

		if (announcementModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				announcementModel = new AnnouncementModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				announcementModel = new AnnouncementModelHibImp();
				// change if JDBC implementation created
			}

			modelCache.put("announcementModel", announcementModel);
		}

		return announcementModel;
	}

	public ResultModelInt getResultModel() {

		ResultModelInt resultModel = (ResultModelInt) modelCache.get("resultModel");

		if (resultModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				resultModel = new ResultModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				resultModel = new ResultModelHibImp();
				// change if JDBC impl created
			}

			modelCache.put("resultModel", resultModel);
		}

		return resultModel;
	}

	public PlacementModelInt getPlacementModel() {

		PlacementModelInt placementModel = (PlacementModelInt) modelCache.get("placementModel");

		if (placementModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				placementModel = new PlacementModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				placementModel = new PlacementModelHibImp(); // change if JDBC impl created
			}

			modelCache.put("placementModel", placementModel);
		}

		return placementModel;
	}

	public HospitalModelInt getHospitalModel() {

		HospitalModelInt hospitalModel = (HospitalModelInt) modelCache.get("hospitalModel");

		if (hospitalModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				hospitalModel = new HospitalModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				hospitalModel = new HospitalModelHibImp(); 
			}

			modelCache.put("hospitalModel", hospitalModel);
		}

		return hospitalModel;
	}

	public VehicleModelInt getVehicleModel() {

		VehicleModelInt vehicleModel = (VehicleModelInt) modelCache.get("vehicleModel");

		if (vehicleModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				vehicleModel = new VehicleModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				vehicleModel = new VehicleModelHibImp(); // change if JDBC impl created
			}

			modelCache.put("vehicleModel", vehicleModel);
		}

		return vehicleModel;
	}

	public EventModelInt getEventModel() {

		EventModelInt eventModel = (EventModelInt) modelCache.get("eventModel");

		if (eventModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				eventModel = new EventModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				eventModel = new EventModelHibImp(); // change if JDBC impl created
			}

			modelCache.put("eventModel", eventModel);
		}

		return eventModel;
	}
	public DonationModelInt getDonationModel() {

		DonationModelInt donationModel = (DonationModelInt) modelCache.get("donationModel");

		if (donationModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				donationModel = new DonationModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				donationModel = new DonationModelHibImp(); // change if JDBC impl created
			}

			modelCache.put("donationModel", donationModel);
		}

		return donationModel;
	}
	public WarrantyModelInt getWarrantyModel() {

		WarrantyModelInt warrantyModel = (WarrantyModelInt) modelCache.get("warrantyModel");

		if (warrantyModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				warrantyModel = new WarrantyModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				warrantyModel = new WarrantyModelHibImp(); // change if JDBC impl created
			}

			modelCache.put("warrantyModel", warrantyModel);
		}

		return warrantyModel;
	}
	public SalaryModelInt getSalaryModel() {

		SalaryModelInt salaryModel = (SalaryModelInt) modelCache.get("salaryModel");

		if (salaryModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				salaryModel = new SalaryModelHibImp();
			}

			if ("JDBC".equals(DATABASE)) {
				salaryModel = new SalaryModelHibImp(); // change if JDBC impl created
			}

			modelCache.put("salaryModel", salaryModel);
		}

		return salaryModel;
	}
	public PortfolioModelInt getPortfolioModel() {

		PortfolioModelInt portfolioModel = (PortfolioModelInt) modelCache.get("portfolioModel");

		if (portfolioModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				portfolioModel = new PortfolioModelHibImpl();
			}

			if ("JDBC".equals(DATABASE)) {
				portfolioModel = new PortfolioModelHibImpl(); // change if JDBC impl created
			}

			modelCache.put("portfolioModel", portfolioModel);
		}

		return portfolioModel;
	}
	
	
	public SecretModelInt getSecretModel() {

		SecretModelInt secretModel = (SecretModelInt) modelCache.get("secretModel");

		if (secretModel == null) {

			if ("Hibernate".equals(DATABASE)) {
				secretModel = new SecretModelHibImpl();
			}

			if ("JDBC".equals(DATABASE)) {
				secretModel = new SecretModelHibImpl(); 
			}

			modelCache.put("secretModel", secretModel);
		}

		return secretModel;
	}
	

	public CustomerModelInt getCustomerModel() {

	    CustomerModelInt customerModel = (CustomerModelInt) modelCache.get("customerModel");

	    if (customerModel == null) {

	        if ("Hibernate".equals(DATABASE)) {
	            customerModel = new CustomerModelHibImpl();
	        }

	        if ("JDBC".equals(DATABASE)) {
	            customerModel = new CustomerModelHibImpl(); 
	        }

	        modelCache.put("customerModel", customerModel);
	    }

	    return customerModel;
	}
	
	public ListenerModelInt getListenerModel() {

	    ListenerModelInt listenerModel = (ListenerModelInt) modelCache.get("listenerModel");

	    if (listenerModel == null) {

	        if ("Hibernate".equals(DATABASE)) {
	            listenerModel = new ListenerModelHibImpl();
	        }

	        if ("JDBC".equals(DATABASE)) {
	            listenerModel = new ListenerModelHibImpl(); 
	        }

	        modelCache.put("listenerModel", listenerModel);
	    }

	    return listenerModel;
	}
}