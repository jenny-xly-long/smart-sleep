/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.era.stride.model;
import com.google.gson.annotations.Expose;

import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 17 "../../../../eRA_model.ump"
public class RecordingSession
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, RecordingSession> recordingsessionsBySessionID = new HashMap<Integer, RecordingSession>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RecordingSession Attributes
  private int sessionID;
  @Expose
  private Date date;
  @Expose
  private Time time;
  @Expose
  private String comment;
  @Expose
  private String phoneStatus;

  //RecordingSession Associations
  @Expose
  private List<Note> notes;
  private ERA eRA;
  @Expose
  private List<DataPoint> dataPoints;
  private Patient patient;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RecordingSession(Date aDate, Time aTime, String aComment, String aPhoneStatus, ERA aERA, Patient aPatient)
  {
    date = aDate;
    time = aTime;
    comment = aComment;
    phoneStatus = aPhoneStatus;
    notes = new ArrayList<Note>();
    boolean didAddERA = setERA(aERA);
    if (!didAddERA)
    {
      throw new RuntimeException("Unable to create recordingSession due to eRA");
    }
    dataPoints = new ArrayList<DataPoint>();
    boolean didAddPatient = setPatient(aPatient);
    if (!didAddPatient)
    {
      throw new RuntimeException("Unable to create recordingSession due to patient");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSessionID(int aSessionID)
  {
    boolean wasSet = false;
    Integer anOldSessionID = getSessionID();
    if (hasWithSessionID(aSessionID)) {
      return wasSet;
    }
    sessionID = aSessionID;
    wasSet = true;
    if (anOldSessionID != null) {
      recordingsessionsBySessionID.remove(anOldSessionID);
    }
    recordingsessionsBySessionID.put(aSessionID, this);
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(Time aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setComment(String aComment)
  {
    boolean wasSet = false;
    comment = aComment;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneStatus(String aPhoneStatus)
  {
    boolean wasSet = false;
    phoneStatus = aPhoneStatus;
    wasSet = true;
    return wasSet;
  }

  public int getSessionID()
  {
    return sessionID;
  }
  /* Code from template attribute_GetUnique */
  public static RecordingSession getWithSessionID(int aSessionID)
  {
    return recordingsessionsBySessionID.get(aSessionID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithSessionID(int aSessionID)
  {
    return getWithSessionID(aSessionID) != null;
  }

  public Date getDate()
  {
    return date;
  }

  public Time getTime()
  {
    return time;
  }

  public String getComment()
  {
    return comment;
  }

  public String getPhoneStatus()
  {
    return phoneStatus;
  }
  /* Code from template association_GetMany */
  public Note getNote(int index)
  {
    Note aNote = notes.get(index);
    return aNote;
  }

  public List<Note> getNotes()
  {
    List<Note> newNotes = Collections.unmodifiableList(notes);
    return newNotes;
  }

  public int numberOfNotes()
  {
    int number = notes.size();
    return number;
  }

  public boolean hasNotes()
  {
    boolean has = notes.size() > 0;
    return has;
  }

  public int indexOfNote(Note aNote)
  {
    int index = notes.indexOf(aNote);
    return index;
  }
  /* Code from template association_GetOne */
  public ERA getERA()
  {
    return eRA;
  }
  /* Code from template association_GetMany */
  public DataPoint getDataPoint(int index)
  {
    DataPoint aDataPoint = dataPoints.get(index);
    return aDataPoint;
  }

  public List<DataPoint> getDataPoints()
  {
    List<DataPoint> newDataPoints = Collections.unmodifiableList(dataPoints);
    return newDataPoints;
  }

  public int numberOfDataPoints()
  {
    int number = dataPoints.size();
    return number;
  }

  public boolean hasDataPoints()
  {
    boolean has = dataPoints.size() > 0;
    return has;
  }

  public int indexOfDataPoint(DataPoint aDataPoint)
  {
    int index = dataPoints.indexOf(aDataPoint);
    return index;
  }
  /* Code from template association_GetOne */
  public Patient getPatient()
  {
    return patient;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Note addNote(Time aStartTime, Time aEndTime, String aComment)
  {
    return new Note(aStartTime, aEndTime, aComment, this);
  }

  public boolean addNote(Note aNote)
  {
    boolean wasAdded = false;
    if (notes.contains(aNote)) { return false; }
    RecordingSession existingRecordingSession = aNote.getRecordingSession();
    boolean isNewRecordingSession = existingRecordingSession != null && !this.equals(existingRecordingSession);
    if (isNewRecordingSession)
    {
      aNote.setRecordingSession(this);
    }
    else
    {
      notes.add(aNote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeNote(Note aNote)
  {
    boolean wasRemoved = false;
    //Unable to remove aNote, as it must always have a recordingSession
    if (!this.equals(aNote.getRecordingSession()))
    {
      notes.remove(aNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addNoteAt(Note aNote, int index)
  {  
    boolean wasAdded = false;
    if(addNote(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNoteAt(Note aNote, int index)
  {
    boolean wasAdded = false;
    if(notes.contains(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNoteAt(aNote, index);
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
      existingERA.removeRecordingSession(this);
    }
    eRA.addRecordingSession(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDataPoints()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DataPoint addDataPoint(double aTime, Sensor aSensor)
  {
    return new DataPoint(aTime, aSensor, this);
  }

  public boolean addDataPoint(DataPoint aDataPoint)
  {
    boolean wasAdded = false;
    if (dataPoints.contains(aDataPoint)) { return false; }
    RecordingSession existingRecordingSession = aDataPoint.getRecordingSession();
    boolean isNewRecordingSession = existingRecordingSession != null && !this.equals(existingRecordingSession);
    if (isNewRecordingSession)
    {
      aDataPoint.setRecordingSession(this);
    }
    else
    {
      dataPoints.add(aDataPoint);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDataPoint(DataPoint aDataPoint)
  {
    boolean wasRemoved = false;
    //Unable to remove aDataPoint, as it must always have a recordingSession
    if (!this.equals(aDataPoint.getRecordingSession()))
    {
      dataPoints.remove(aDataPoint);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDataPointAt(DataPoint aDataPoint, int index)
  {  
    boolean wasAdded = false;
    if(addDataPoint(aDataPoint))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDataPoints()) { index = numberOfDataPoints() - 1; }
      dataPoints.remove(aDataPoint);
      dataPoints.add(index, aDataPoint);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDataPointAt(DataPoint aDataPoint, int index)
  {
    boolean wasAdded = false;
    if(dataPoints.contains(aDataPoint))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDataPoints()) { index = numberOfDataPoints() - 1; }
      dataPoints.remove(aDataPoint);
      dataPoints.add(index, aDataPoint);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDataPointAt(aDataPoint, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPatient(Patient aPatient)
  {
    boolean wasSet = false;
    if (aPatient == null)
    {
      return wasSet;
    }

    Patient existingPatient = patient;
    patient = aPatient;
    if (existingPatient != null && !existingPatient.equals(aPatient))
    {
      existingPatient.removeRecordingSession(this);
    }
    patient.addRecordingSession(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    recordingsessionsBySessionID.remove(getSessionID());
    while (notes.size() > 0)
    {
      Note aNote = notes.get(notes.size() - 1);
      aNote.delete();
      notes.remove(aNote);
    }
    
    ERA placeholderERA = eRA;
    this.eRA = null;
    if(placeholderERA != null)
    {
      placeholderERA.removeRecordingSession(this);
    }
    while (dataPoints.size() > 0)
    {
      DataPoint aDataPoint = dataPoints.get(dataPoints.size() - 1);
      aDataPoint.delete();
      dataPoints.remove(aDataPoint);
    }
    
    Patient placeholderPatient = patient;
    this.patient = null;
    if(placeholderPatient != null)
    {
      placeholderPatient.removeRecordingSession(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "sessionID" + ":" + getSessionID()+ "," +
            "comment" + ":" + getComment()+ "," +
            "phoneStatus" + ":" + getPhoneStatus()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "time" + "=" + (getTime() != null ? !getTime().equals(this)  ? getTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "eRA = "+(getERA()!=null?Integer.toHexString(System.identityHashCode(getERA())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "patient = "+(getPatient()!=null?Integer.toHexString(System.identityHashCode(getPatient())):"null");
  }
}