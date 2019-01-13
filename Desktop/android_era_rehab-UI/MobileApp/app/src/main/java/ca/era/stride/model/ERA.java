/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.era.stride.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 3 "../../../../eRA_model.ump"
public class ERA
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ERA Associations
  private List<User> users;
  private List<Sensor> sensors;
  private List<Patient> patients;
  private List<RecordingSession> recordingSessions;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ERA()
  {
    users = new ArrayList<User>();
    sensors = new ArrayList<Sensor>();
    patients = new ArrayList<Patient>();
    recordingSessions = new ArrayList<RecordingSession>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Sensor getSensor(int index)
  {
    Sensor aSensor = sensors.get(index);
    return aSensor;
  }

  public List<Sensor> getSensors()
  {
    List<Sensor> newSensors = Collections.unmodifiableList(sensors);
    return newSensors;
  }

  public int numberOfSensors()
  {
    int number = sensors.size();
    return number;
  }

  public boolean hasSensors()
  {
    boolean has = sensors.size() > 0;
    return has;
  }

  public int indexOfSensor(Sensor aSensor)
  {
    int index = sensors.indexOf(aSensor);
    return index;
  }
  /* Code from template association_GetMany */
  public Patient getPatient(int index)
  {
    Patient aPatient = patients.get(index);
    return aPatient;
  }

  public List<Patient> getPatients()
  {
    List<Patient> newPatients = Collections.unmodifiableList(patients);
    return newPatients;
  }

  public int numberOfPatients()
  {
    int number = patients.size();
    return number;
  }

  public boolean hasPatients()
  {
    boolean has = patients.size() > 0;
    return has;
  }

  public int indexOfPatient(Patient aPatient)
  {
    int index = patients.indexOf(aPatient);
    return index;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aUsername, String aSessionToken)
  {
    return new User(aUsername, aSessionToken, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ERA existingERA = aUser.getERA();
    boolean isNewERA = existingERA != null && !this.equals(existingERA);
    if (isNewERA)
    {
      aUser.setERA(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a eRA
    if (!this.equals(aUser.getERA()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSensors()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Sensor addSensor(String aSensorType, String aSensorModel, int aSampleRate)
  {
    return new Sensor(aSensorType, aSensorModel, aSampleRate, this);
  }

  public boolean addSensor(Sensor aSensor)
  {
    boolean wasAdded = false;
    if (sensors.contains(aSensor)) { return false; }
    ERA existingERA = aSensor.getERA();
    boolean isNewERA = existingERA != null && !this.equals(existingERA);
    if (isNewERA)
    {
      aSensor.setERA(this);
    }
    else
    {
      sensors.add(aSensor);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSensor(Sensor aSensor)
  {
    boolean wasRemoved = false;
    //Unable to remove aSensor, as it must always have a eRA
    if (!this.equals(aSensor.getERA()))
    {
      sensors.remove(aSensor);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSensorAt(Sensor aSensor, int index)
  {  
    boolean wasAdded = false;
    if(addSensor(aSensor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSensors()) { index = numberOfSensors() - 1; }
      sensors.remove(aSensor);
      sensors.add(index, aSensor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSensorAt(Sensor aSensor, int index)
  {
    boolean wasAdded = false;
    if(sensors.contains(aSensor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSensors()) { index = numberOfSensors() - 1; }
      sensors.remove(aSensor);
      sensors.add(index, aSensor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSensorAt(aSensor, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPatients()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Patient addPatient(String aSurname, String aName, String aGender, Date aBirthDate, float aHeight, float aWeight)
  {
    return new Patient(aSurname, aName, aGender, aBirthDate, aHeight, aWeight, this);
  }

  public boolean addPatient(Patient aPatient)
  {
    boolean wasAdded = false;
    if (patients.contains(aPatient)) { return false; }
    ERA existingERA = aPatient.getERA();
    boolean isNewERA = existingERA != null && !this.equals(existingERA);
    if (isNewERA)
    {
      aPatient.setERA(this);
    }
    else
    {
      patients.add(aPatient);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePatient(Patient aPatient)
  {
    boolean wasRemoved = false;
    //Unable to remove aPatient, as it must always have a eRA
    if (!this.equals(aPatient.getERA()))
    {
      patients.remove(aPatient);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPatientAt(Patient aPatient, int index)
  {  
    boolean wasAdded = false;
    if(addPatient(aPatient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPatients()) { index = numberOfPatients() - 1; }
      patients.remove(aPatient);
      patients.add(index, aPatient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePatientAt(Patient aPatient, int index)
  {
    boolean wasAdded = false;
    if(patients.contains(aPatient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPatients()) { index = numberOfPatients() - 1; }
      patients.remove(aPatient);
      patients.add(index, aPatient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPatientAt(aPatient, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRecordingSessions()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public RecordingSession addRecordingSession(Date aDate, Time aTime, String aComment, String aPhoneStatus, Patient aPatient)
  {
    return new RecordingSession(aDate, aTime, aComment, aPhoneStatus, this, aPatient);
  }

  public boolean addRecordingSession(RecordingSession aRecordingSession)
  {
    boolean wasAdded = false;
    if (recordingSessions.contains(aRecordingSession)) { return false; }
    ERA existingERA = aRecordingSession.getERA();
    boolean isNewERA = existingERA != null && !this.equals(existingERA);
    if (isNewERA)
    {
      aRecordingSession.setERA(this);
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
    //Unable to remove aRecordingSession, as it must always have a eRA
    if (!this.equals(aRecordingSession.getERA()))
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

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (sensors.size() > 0)
    {
      Sensor aSensor = sensors.get(sensors.size() - 1);
      aSensor.delete();
      sensors.remove(aSensor);
    }
    
    while (patients.size() > 0)
    {
      Patient aPatient = patients.get(patients.size() - 1);
      aPatient.delete();
      patients.remove(aPatient);
    }
    
    while (recordingSessions.size() > 0)
    {
      RecordingSession aRecordingSession = recordingSessions.get(recordingSessions.size() - 1);
      aRecordingSession.delete();
      recordingSessions.remove(aRecordingSession);
    }
    
  }

}