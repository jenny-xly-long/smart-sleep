/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.era.stride.model;
import com.google.gson.annotations.Expose;

import java.util.*;

// line 10 "../../../../eRA_model.ump"
public class DataPoint
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DataPoint Attributes
  @Expose
  private double time;
  @Expose
  private List<Double> value;

  //DataPoint Associations
  @Expose
  private Sensor sensor;
  private RecordingSession recordingSession;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DataPoint(double aTime, Sensor aSensor, RecordingSession aRecordingSession)
  {
    time = aTime;
    value = new ArrayList<Double>();
    if (!setSensor(aSensor))
    {
      throw new RuntimeException("Unable to create DataPoint due to aSensor");
    }
    boolean didAddRecordingSession = setRecordingSession(aRecordingSession);
    if (!didAddRecordingSession)
    {
      throw new RuntimeException("Unable to create dataPoint due to recordingSession");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTime(double aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetMany */
  public boolean addValue(Double aValue)
  {
    boolean wasAdded = false;
    wasAdded = value.add(aValue);
    return wasAdded;
  }

  public boolean removeValue(Double aValue)
  {
    boolean wasRemoved = false;
    wasRemoved = value.remove(aValue);
    return wasRemoved;
  }

  public double getTime()
  {
    return time;
  }
  /* Code from template attribute_GetMany */
  public Double getValue(int index)
  {
    Double aValue = value.get(index);
    return aValue;
  }

  public Double[] getValue()
  {
    Double[] newValue = value.toArray(new Double[value.size()]);
    return newValue;
  }

  public int numberOfValue()
  {
    int number = value.size();
    return number;
  }

  public boolean hasValue()
  {
    boolean has = value.size() > 0;
    return has;
  }

  public int indexOfValue(Double aValue)
  {
    int index = value.indexOf(aValue);
    return index;
  }
  /* Code from template association_GetOne */
  public Sensor getSensor()
  {
    return sensor;
  }
  /* Code from template association_GetOne */
  public RecordingSession getRecordingSession()
  {
    return recordingSession;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setSensor(Sensor aNewSensor)
  {
    boolean wasSet = false;
    if (aNewSensor != null)
    {
      sensor = aNewSensor;
      wasSet = true;
    }
    return wasSet;
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
      existingRecordingSession.removeDataPoint(this);
    }
    recordingSession.addDataPoint(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    sensor = null;
    RecordingSession placeholderRecordingSession = recordingSession;
    this.recordingSession = null;
    if(placeholderRecordingSession != null)
    {
      placeholderRecordingSession.removeDataPoint(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "time" + ":" + getTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sensor = "+(getSensor()!=null?Integer.toHexString(System.identityHashCode(getSensor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "recordingSession = "+(getRecordingSession()!=null?Integer.toHexString(System.identityHashCode(getRecordingSession())):"null");
  }
}