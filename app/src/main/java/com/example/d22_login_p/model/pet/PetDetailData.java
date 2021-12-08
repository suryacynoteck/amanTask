
package com.example.d22_login_p.model.pet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PetDetailData {        //TODO:  update1 wrt POSTMAN @POST req.

    @SerializedName("id")
    @Expose
    private Double id;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("petCategoryId")
    @Expose
    private Integer petCategoryId;
    @SerializedName("petSexId")
    @Expose
    private Double petSexId;
    @SerializedName("petAgeId")
    @Expose
    private Double petAgeId;
    @SerializedName("petSizeId")
    @Expose
    private Double petSizeId;
    @SerializedName("petColorId")
    @Expose
    private Double petColorId;
    @SerializedName("petBreedId")
    @Expose
    private Double petBreedId;
    @SerializedName("petBreedId2")
    @Expose
    private Double petBreedId2;
    @SerializedName("petName")
    @Expose
    private String petName;
    @SerializedName("slotId")
    @Expose
    private Object slotId;
    @SerializedName("problemDescription")
    @Expose
    private Object problemDescription;
    @SerializedName("petId")
    @Expose
    private Object petId;
    @SerializedName("petParentName")
    @Expose
    private String petParentName;
    @SerializedName("prefix")
    @Expose
    private Object prefix;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;

//    @SerializedName("address")          // TODO: why Obj return type
//    @Expose
//    private Object address;

    @SerializedName("address")
    @Expose
    private String  address;

    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("petAgeNumber")
    @Expose
    private Double petAgeNumber;
    @SerializedName("petAgeListId")
    @Expose
    private Integer petAgeListId;
    @SerializedName("petAgeUnitList")
    @Expose
    private List<PetDetailDataPetAgeUnit> petAgeUnitList = null;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("createDate")
    @Expose
    private String createDate;

    @SerializedName("petProfileImageUrl")
    @Expose
    private String  petProfileImageUrl;

    @SerializedName("firstServiceImageUrl")
    @Expose
    private Object firstServiceImageUrl;
    @SerializedName("secondServiceImageUrl")
    @Expose
    private Object secondServiceImageUrl;
    @SerializedName("thirdServiceImageUrl")
    @Expose
    private Object thirdServiceImageUrl;
    @SerializedName("fourthServiceImageUrl")
    @Expose
    private Object fourthServiceImageUrl;
    @SerializedName("fifthServiceImageUrl")
    @Expose
    private Object fifthServiceImageUrl;
    @SerializedName("encryptedId")
    @Expose
    private String encryptedId;
    @SerializedName("ageOfPet")
    @Expose
    private Integer ageOfPet;
    @SerializedName("vaccinationHistory")
    @Expose
    private Integer vaccinationHistory;
    @SerializedName("lastVisitEncryptedId")
    @Expose
    private String lastVisitEncryptedId;
    @SerializedName("licenceNumber")
    @Expose
    private Object licenceNumber;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("validTill")
    @Expose
    private Object validTill;
    @SerializedName("balance")
    @Expose
    private Object balance;
    @SerializedName("isAdopted")
    @Expose
    private Boolean isAdopted;
    @SerializedName("isDonated")
    @Expose
    private Boolean isDonated;
    @SerializedName("microchipNumber")
    @Expose
    private Object microchipNumber;

    @SerializedName("user")
    @Expose
    private PetDetailDataUser user;

    @SerializedName("petTypeList")
    @Expose
    private List<PetDetailDataPetType> petTypeList = null;
    @SerializedName("petSexList")
    @Expose
    private List<PetDetailDataPetSex> petSexList = null;
    @SerializedName("petAgeList")
    @Expose
    private List<PetDetailDataPetAge> petAgeList = null;
    @SerializedName("petSizeList")
    @Expose
    private List<PetDetailDataPetSize> petSizeList = null;
    @SerializedName("petColorList")
    @Expose
    private List<PetColor> petColorList = null;
    @SerializedName("petBreedList")
    @Expose
    private List<PetDetailDataPetBreed> petBreedList = null;
    @SerializedName("cityList")
    @Expose
    private Object cityList;
    @SerializedName("stateList")
    @Expose
    private Object stateList;
    @SerializedName("petType")
    @Expose
    private Object petType;
    @SerializedName("petSex")
    @Expose
    private String petSex;
    @SerializedName("petAge")
    @Expose
    private String petAge;
    @SerializedName("petSize")
    @Expose
    private String petSize;
    @SerializedName("petColor")
    @Expose
    private String petColor;
    @SerializedName("petCategory")
    @Expose
    private String petCategory;
    @SerializedName("petBreed")
    @Expose
    private String petBreed;
    @SerializedName("role")
    @Expose
    private Object role;
    @SerializedName("otherBreedName")
    @Expose
    private Object otherBreedName;
    @SerializedName("otherColorName")
    @Expose
    private Object otherColorName;
    @SerializedName("otherSizeName")
    @Expose
    private Object otherSizeName;
    @SerializedName("otherAgeName")
    @Expose
    private Object otherAgeName;
    @SerializedName("numberOfRecords")
    @Expose
    private Integer numberOfRecords;
    @SerializedName("otherCategory")
    @Expose
    private Object otherCategory;
    @SerializedName("pageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("petUniqueId")
    @Expose
    private String petUniqueId;
    @SerializedName("encryptedSlotId")
    @Expose
    private Object encryptedSlotId;
    @SerializedName("isAddedToRegister")
    @Expose
    private Boolean isAddedToRegister;
    @SerializedName("barcodeUrl")
    @Expose
    private String barcodeUrl;
    @SerializedName("prefixList")
    @Expose
    private List<String> prefixList = null;
    @SerializedName("encryptedMunicipalCorporationId")
    @Expose
    private Object encryptedMunicipalCorporationId;
    @SerializedName("hasPrescription")
    @Expose
    private Boolean hasPrescription;
    @SerializedName("petDetailImageList")
    @Expose
    private List<Object> petDetailImageList = null;
    @SerializedName("petTestsAndXrey")
    @Expose
    private List<Object> petTestsAndXrey = null;
    @SerializedName("petClinicVisit")
    @Expose
    private List<Object> petClinicVisit = null;
    @SerializedName("petHospitalizationsSurgeries")
    @Expose
    private List<Object> petHospitalizationsSurgeries = null;
    @SerializedName("petLabwork")
    @Expose
    private List<Object> petLabwork = null;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPetCategoryId() {
        return petCategoryId;
    }

    public void setPetCategoryId(Integer petCategoryId) {
        this.petCategoryId = petCategoryId;
    }

    public Double getPetSexId() {
        return petSexId;
    }

    public void setPetSexId(Double petSexId) {
        this.petSexId = petSexId;
    }

    public Double getPetAgeId() {
        return petAgeId;
    }

    public void setPetAgeId(Double petAgeId) {
        this.petAgeId = petAgeId;
    }

    public Double getPetSizeId() {
        return petSizeId;
    }

    public void setPetSizeId(Double petSizeId) {
        this.petSizeId = petSizeId;
    }

    public Double getPetColorId() {
        return petColorId;
    }

    public void setPetColorId(Double petColorId) {
        this.petColorId = petColorId;
    }

    public Double getPetBreedId() {
        return petBreedId;
    }

    public void setPetBreedId(Double petBreedId) {
        this.petBreedId = petBreedId;
    }

    public Double getPetBreedId2() {
        return petBreedId2;
    }

    public void setPetBreedId2(Double petBreedId2) {
        this.petBreedId2 = petBreedId2;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Object getSlotId() {
        return slotId;
    }

    public void setSlotId(Object slotId) {
        this.slotId = slotId;
    }

    public Object getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(Object problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Object getPetId() {
        return petId;
    }

    public void setPetId(Object petId) {
        this.petId = petId;
    }

    public String getPetParentName() {
        return petParentName;
    }

    public void setPetParentName(String petParentName) {
        this.petParentName = petParentName;
    }

    public Object getPrefix() {
        return prefix;
    }

    public void setPrefix(Object prefix) {
        this.prefix = prefix;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Object getAddress() {
        return address;
    }

    public String  getAddress_str() {
        return address;
    }

//    public void setAddress(Object address) {
//        this.address = address;
//    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getPetAgeNumber() {
        return petAgeNumber;
    }

    public void setPetAgeNumber(Double petAgeNumber) {
        this.petAgeNumber = petAgeNumber;
    }

    public Integer getPetAgeListId() {
        return petAgeListId;
    }

    public void setPetAgeListId(Integer petAgeListId) {
        this.petAgeListId = petAgeListId;
    }

    public List<PetDetailDataPetAgeUnit> getPetAgeUnitList() {
        return petAgeUnitList;
    }

    public void setPetAgeUnitList(List<PetDetailDataPetAgeUnit> petAgeUnitList) {
        this.petAgeUnitList = petAgeUnitList;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String  getPetProfileImageUrl() {
        return petProfileImageUrl;
    }



    public Object getFirstServiceImageUrl() {
        return firstServiceImageUrl;
    }

    public void setFirstServiceImageUrl(Object firstServiceImageUrl) {
        this.firstServiceImageUrl = firstServiceImageUrl;
    }

    public Object getSecondServiceImageUrl() {
        return secondServiceImageUrl;
    }

    public void setSecondServiceImageUrl(Object secondServiceImageUrl) {
        this.secondServiceImageUrl = secondServiceImageUrl;
    }

    public Object getThirdServiceImageUrl() {
        return thirdServiceImageUrl;
    }

    public void setThirdServiceImageUrl(Object thirdServiceImageUrl) {
        this.thirdServiceImageUrl = thirdServiceImageUrl;
    }

    public Object getFourthServiceImageUrl() {
        return fourthServiceImageUrl;
    }

    public void setFourthServiceImageUrl(Object fourthServiceImageUrl) {
        this.fourthServiceImageUrl = fourthServiceImageUrl;
    }

    public Object getFifthServiceImageUrl() {
        return fifthServiceImageUrl;
    }

    public void setFifthServiceImageUrl(Object fifthServiceImageUrl) {
        this.fifthServiceImageUrl = fifthServiceImageUrl;
    }

    public String getEncryptedId() {
        return encryptedId;
    }

    public void setEncryptedId(String encryptedId) {
        this.encryptedId = encryptedId;
    }

    public Integer getAgeOfPet() {
        return ageOfPet;
    }

    public void setAgeOfPet(Integer ageOfPet) {
        this.ageOfPet = ageOfPet;
    }

    public Integer getVaccinationHistory() {
        return vaccinationHistory;
    }

    public void setVaccinationHistory(Integer vaccinationHistory) {
        this.vaccinationHistory = vaccinationHistory;
    }

    public String getLastVisitEncryptedId() {
        return lastVisitEncryptedId;
    }

    public void setLastVisitEncryptedId(String lastVisitEncryptedId) {
        this.lastVisitEncryptedId = lastVisitEncryptedId;
    }

    public Object getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(Object licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getValidTill() {
        return validTill;
    }

    public void setValidTill(Object validTill) {
        this.validTill = validTill;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = balance;
    }

    public Boolean getIsAdopted() {
        return isAdopted;
    }

    public void setIsAdopted(Boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public Boolean getIsDonated() {
        return isDonated;
    }

    public void setIsDonated(Boolean isDonated) {
        this.isDonated = isDonated;
    }

    public Object getMicrochipNumber() {
        return microchipNumber;
    }

    public void setMicrochipNumber(Object microchipNumber) {
        this.microchipNumber = microchipNumber;
    }

    public PetDetailDataUser getUser() {
        return user;
    }

    public void setUser(PetDetailDataUser user) {
        this.user = user;
    }

    public List<PetDetailDataPetType> getPetTypeList() {
        return petTypeList;
    }

    public void setPetTypeList(List<PetDetailDataPetType> petTypeList) {
        this.petTypeList = petTypeList;
    }

    public List<PetDetailDataPetSex> getPetSexList() {
        return petSexList;
    }

    public void setPetSexList(List<PetDetailDataPetSex> petSexList) {
        this.petSexList = petSexList;
    }

    public List<PetDetailDataPetAge> getPetAgeList() {
        return petAgeList;
    }

    public void setPetAgeList(List<PetDetailDataPetAge> petAgeList) {
        this.petAgeList = petAgeList;
    }

    public List<PetDetailDataPetSize> getPetSizeList() {
        return petSizeList;
    }

    public void setPetSizeList(List<PetDetailDataPetSize> petSizeList) {
        this.petSizeList = petSizeList;
    }

    public List<PetColor> getPetColorList() {
        return petColorList;
    }

    public void setPetColorList(List<PetColor> petColorList) {
        this.petColorList = petColorList;
    }

    public List<PetDetailDataPetBreed> getPetBreedList() {
        return petBreedList;
    }

    public void setPetBreedList(List<PetDetailDataPetBreed> petBreedList) {
        this.petBreedList = petBreedList;
    }

    public Object getCityList() {
        return cityList;
    }

    public void setCityList(Object cityList) {
        this.cityList = cityList;
    }

    public Object getStateList() {
        return stateList;
    }

    public void setStateList(Object stateList) {
        this.stateList = stateList;
    }

    public Object getPetType() {
        return petType;
    }

    public void setPetType(Object petType) {
        this.petType = petType;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public Object getOtherBreedName() {
        return otherBreedName;
    }

    public void setOtherBreedName(Object otherBreedName) {
        this.otherBreedName = otherBreedName;
    }

    public Object getOtherColorName() {
        return otherColorName;
    }

    public void setOtherColorName(Object otherColorName) {
        this.otherColorName = otherColorName;
    }

    public Object getOtherSizeName() {
        return otherSizeName;
    }

    public void setOtherSizeName(Object otherSizeName) {
        this.otherSizeName = otherSizeName;
    }

    public Object getOtherAgeName() {
        return otherAgeName;
    }

    public void setOtherAgeName(Object otherAgeName) {
        this.otherAgeName = otherAgeName;
    }

    public Integer getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(Integer numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public Object getOtherCategory() {
        return otherCategory;
    }

    public void setOtherCategory(Object otherCategory) {
        this.otherCategory = otherCategory;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPetUniqueId() {
        return petUniqueId;
    }

    public void setPetUniqueId(String petUniqueId) {
        this.petUniqueId = petUniqueId;
    }

    public Object getEncryptedSlotId() {
        return encryptedSlotId;
    }

    public void setEncryptedSlotId(Object encryptedSlotId) {
        this.encryptedSlotId = encryptedSlotId;
    }

    public Boolean getIsAddedToRegister() {
        return isAddedToRegister;
    }

    public void setIsAddedToRegister(Boolean isAddedToRegister) {
        this.isAddedToRegister = isAddedToRegister;
    }

    public String getBarcodeUrl() {
        return barcodeUrl;
    }

    public void setBarcodeUrl(String barcodeUrl) {
        this.barcodeUrl = barcodeUrl;
    }

    public List<String> getPrefixList() {
        return prefixList;
    }

    public void setPrefixList(List<String> prefixList) {
        this.prefixList = prefixList;
    }

    public Object getEncryptedMunicipalCorporationId() {
        return encryptedMunicipalCorporationId;
    }

    public void setEncryptedMunicipalCorporationId(Object encryptedMunicipalCorporationId) {
        this.encryptedMunicipalCorporationId = encryptedMunicipalCorporationId;
    }

    public Boolean getHasPrescription() {
        return hasPrescription;
    }

    public void setHasPrescription(Boolean hasPrescription) {
        this.hasPrescription = hasPrescription;
    }

    public List<Object> getPetDetailImageList() {
        return petDetailImageList;
    }

    public void setPetDetailImageList(List<Object> petDetailImageList) {
        this.petDetailImageList = petDetailImageList;
    }

    public List<Object> getPetTestsAndXrey() {
        return petTestsAndXrey;
    }

    public void setPetTestsAndXrey(List<Object> petTestsAndXrey) {
        this.petTestsAndXrey = petTestsAndXrey;
    }

    public List<Object> getPetClinicVisit() {
        return petClinicVisit;
    }

    public void setPetClinicVisit(List<Object> petClinicVisit) {
        this.petClinicVisit = petClinicVisit;
    }

    public List<Object> getPetHospitalizationsSurgeries() {
        return petHospitalizationsSurgeries;
    }

    public void setPetHospitalizationsSurgeries(List<Object> petHospitalizationsSurgeries) {
        this.petHospitalizationsSurgeries = petHospitalizationsSurgeries;
    }

    public List<Object> getPetLabwork() {
        return petLabwork;
    }

    public void setPetLabwork(List<Object> petLabwork) {
        this.petLabwork = petLabwork;
    }

}

