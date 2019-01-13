/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.era.stride.model;
import com.google.gson.annotations.Expose;

import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 38 "../../../../eRA_model.ump"
public class Patient
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Patient> patientsByPatientID = new HashMap<Integer, Patient>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Patient Attributes
  private int patientID;
  @Expose
  private String surname;
  @Expose
  private String name;
  @Expose
  private String gender;
  @Expose
  private Date birthDate;
  @Expose
  private float height;
  @Expose
  private float weight;

  //Patient Associations
  private List<RecordingSession> recordingSessions;
  private ERA eRA;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Patient(String aSurname, String aName, String aGender, Date aBirthDate, float aHeight, float aWeight, ERA aERA)
  {
    surname = aSurname;
    name = aName;
    gender = aGender;
    birthDate = aBirthDate;
    height = aHeight;
    weight = aWeight;
    recordingSessions = new ArrayList<RecordingSession>();
    boolean didAddERA = setERA(aERA);
    if (!didAddERA)
    {
      throw new RuntimeException("Unable to create patient due to eRA");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPatientID(int aPatientID)
  {
    boolean wasSet = false;
    Integer anOldPatientID = getPatientID();
    if (hasWithPatientID(aPatientID)) {
      return wasSet;
    }
    patientID = aPatientID;
    wasSet = true;
    if (anOldPatientID != null) {
      patientsByPatientID.remove(anOldPatientID);
    }
    patientsByPatientID.put(aPatientID, this);
    return wasSet;
  }

  public boolean setSurname(String aSurname)
  {
    boolean wasSet = false;
    surname = aSurname;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setGender(String aGender)
  {
    boolean wasSet = false;
    gender = aGender;
    wasSet = true;
    return wasSet;
  }

  public boolean setBirthDate(Date aBirthDate)
  {
    boolean wasSet = false;
    birthDate = aBirthDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(float aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(float aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public int getPatientID()
  {
    return patientID;
  }
  /* Code from template attribute_GetUnique */
  public static Patient getWithPatientID(int aPatientID)
  {
    return patientsByPatientID.get(aPatientID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithPatientID(int aPatientID)
  {
    return getWithPatientID(aPatientID) != null;
  }

  public String getSurname()
  {
    return surname;
  }

  public String getName()
  {
    return name;
  }

  public String getGender()
  {
    return gender;
  }

  public Date getBirthDate()
  {
    return birthDate;
  }

  public float getHeight()
  {
    return height;
  }

  public float getWeight()
  {
    return weight;
  }
  /* Code from template association_GetMany */
  public RecordingSession getRecordingSession(int index)
  {
    RecordingSession aRecordingSession = recordingSessions.get(index);
    return aRecordingSession;
  }

  public List<RecordingSession> getRecordingSessions()
  {
    List<RecordingSession> newRecordingSessions = Collections.unmodifiableList(recordingSessions);
    return newRecordingSessions;
  }

  public int numberOfRecordingSessions()
  {
    int number = recordingSessions.size();
    return number;
  }

  public boolean hasRecordingSessions()
  {
    boolean has = recordingSessions.size() > 0;
    return has;
  }

  public int indexOfRecordingSession(RecordingSession aRecordingSession)
  {
    int index = recordingSessions.indexOf(aRecordingSession);
    return index;
  }
  /* Code from template association_GetOne */
  public ERA getERA()
  {
    return eRA;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRecordingSessions()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public RecordingSession addRecordingSession(Date aDate, Time aTime, String aComment, String aPhoneStatus, ERA aERA)
  {
    return new RecordingSession(aDate, aTime, aComment, aPhoneStatus, aERA, this);
  }

  public boolean addRecordingSession(RecordingSession aRecordingSession)
  {
    boolean wasAdded = false;
    if (recordingSessions.contains(aRecordingSession)) { return false; }
    Patient existingPatient = aRecordingSession.getPatient();
    boolean isNewPatient = existingPatient != null && !this.equals(existingPatient);
    if (isNewPatient)
    {
      aRecordingSession.setPatient(this);
    }
    else
    {
      recordingSessions.add(aRecordingSession);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRecordingSession(RecordingSession aRecordingSession)
  {
    boolean wasRemoved = false;
    //Unable to remove aRecordingSession, as it must always have a patient
    if (!this.equals(aRecordingSession.getPatient()))
    {
      recordingSessions.remove(aRecordingSession);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRecordingSessionAt(RecordingSession aRecordingSession, int index)
  {  
    boolean wasAdded = false;
    if(addRecordingSession(aRecordingSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecordingSessions()) { index = numberOfRecordingSessions() - 1; }
      recordingSessions.remove(aRecordingSession);
      recordingSessions.add(index, aRecordingSession);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRecordingSessionAt(RecordingSession aRecordingSession, int index)
  {
    boolean wasAdded = false;
    if(recordingSessions.contains(aRecordingSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecordingSessions()) { index = numberOfRecordingSessions() - 1; }
      recordingSessions.remove(aRecordingSession);
      recordingSessions.add(index, aRecordingSession);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRecordingSessionAt(aRecordingSession, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setERA(ERA aERA)
  {
    boolean wasSet = false;
    if (aERA == null)
    {
      return wasSet;
    }

    ERA existingERA = eRA;
    eRA = aERA;
    if (existingERA != null && !existingERA.equals(aERA))
    {
      existingERA.removePatient(this);
    }
    eRA.addPatient(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    patientsByPatientID.remove(getPatientID());
    for(int i=recordingSessions.size(); i > 0; i--)
    {
      RecordingSession aRecordingSession = recordingSessions.get(i - 1);
      aRecordingSession.delete();
    }
    ERA placeholderERA = eRA;
    this.eRA = null;
    if(placeholderERA != null)
    {
      placeholderERA.removePatient(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "patientID" + ":" + getPatientID()+ "," +
            "surname" + ":" + getSurname()+ "," +
            "name" + ":" + getName()+ "," +
            "gender" + ":" + getGender()+ "," +
            "height" + ":" + getHeight()+ "," +
            "weight" + ":" + getWeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "birthDate" + "=" + (getBirthDate() != null ? !getBirthDate().equals(this)  ? getBirthDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "eRA = "+(getERA()!=null?Integer.toHexString(System.identityHashCode(getERA())):"null");
  }
}