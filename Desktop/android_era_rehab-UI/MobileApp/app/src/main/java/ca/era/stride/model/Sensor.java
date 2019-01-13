/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.era.stride.model;

import com.google.gson.annotations.Expose;

// line 32 "../../../../eRA_model.ump"
public class Sensor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Sensor Attributes
  @Expose
  private String sensorType;
  @Expose
  private String sensorModel;
  @Expose
  private int sampleRate;

  //Sensor Associations
  private ERA eRA;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Sensor(String aSensorType, String aSensorModel, int aSampleRate, ERA aERA)
  {
    sensorType = aSensorType;
    sensorModel = aSensorModel;
    sampleRate = aSampleRate;
    boolean didAddERA = setERA(aERA);
    if (!didAddERA)
    {
      throw new RuntimeException("Unable to create sensor due to eRA");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSensorType(String aSensorType)
  {
    boolean wasSet = false;
    sensorType = aSensorType;
    wasSet = true;
    return wasSet;
  }

  public boolean setSensorModel(String aSensorModel)
  {
    boolean wasSet = false;
    sensorModel = aSensorModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setSampleRate(int aSampleRate)
  {
    boolean wasSet = false;
    sampleRate = aSampleRate;
    wasSet = true;
    return wasSet;
  }

  public String getSensorType()
  {
    return sensorType;
  }

  public String getSensorModel()
  {
    return sensorModel;
  }

  public int getSampleRate()
  {
    return sampleRate;
  }
  /* Code from template association_GetOne */
  public ERA getERA()
  {
    return eRA;
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
      existingERA.removeSensor(this);
    }
    eRA.addSensor(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ERA placeholderERA = eRA;
    this.eRA = null;
    if(placeholderERA != null)
    {
      placeholderERA.removeSensor(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "sensorType" + ":" + getSensorType()+ "," +
            "sensorModel" + ":" + getSensorModel()+ "," +
            "sampleRate" + ":" + getSampleRate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "eRA = "+(getERA()!=null?Integer.toHexString(System.identityHashCode(getERA())):"null");
  }
}