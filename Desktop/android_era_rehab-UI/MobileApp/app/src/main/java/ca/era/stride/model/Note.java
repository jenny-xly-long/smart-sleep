/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.era.stride.model;
import com.google.gson.annotations.Expose;

import java.sql.Time;

// line 26 "../../../../eRA_model.ump"
public class Note
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Note Attributes
  @Expose
  private Time startTime;
  @Expose
  private Time endTime;
  @Expose
  private String comment;

  //Note Associations
  private RecordingSession recordingSession;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Note(Time aStartTime, Time aEndTime, String aComment, RecordingSession aRecordingSession)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    comment = aComment;
    boolean didAddRecordingSession = setRecordingSession(aRecordingSession);
    if (!didAddRecordingSession)
    {
      throw new RuntimeException("Unable to create note due to recordingSession");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
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

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public String getComment()
  {
    return comment;
  }
  /* Code from template association_GetOne */
  public RecordingSession getRecordingSession()
  {
    return recordingSession;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRecordingSession(RecordingSession aRecordingSession)
  {
    boolean wasSet = false;
    if (aRecordingSession == null)
    {
      return wasSet;
    }

    RecordingSession existingRecordingSession = recordingSession;
    recordingSession = aRecordingSession;
    if (existingRecordingSession != null && !existingRecordingSession.equals(aRecordingSession))
    {
      existingRecordingSession.removeNote(this);
    }
    recordingSession.addNote(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    RecordingSession placeholderRecordingSession = recordingSession;
    this.recordingSession = null;
    if(placeholderRecordingSession != null)
    {
      placeholderRecordingSession.removeNote(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "comment" + ":" + getComment()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "recordingSession = "+(getRecordingSession()!=null?Integer.toHexString(System.identityHashCode(getRecordingSession())):"null");
  }
}