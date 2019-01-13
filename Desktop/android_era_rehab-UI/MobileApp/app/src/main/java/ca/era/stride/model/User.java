/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.era.stride.model;

// line 49 "../../../../eRA_model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;
  private String sessionToken;

  //User Associations
  private ERA eRA;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUsername, String aSessionToken, ERA aERA)
  {
    username = aUsername;
    sessionToken = aSessionToken;
    boolean didAddERA = setERA(aERA);
    if (!didAddERA)
    {
      throw new RuntimeException("Unable to create user due to eRA");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setSessionToken(String aSessionToken)
  {
    boolean wasSet = false;
    sessionToken = aSessionToken;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }

  public String getSessionToken()
  {
    return sessionToken;
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
      existingERA.removeUser(this);
    }
    eRA.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ERA placeholderERA = eRA;
    this.eRA = null;
    if(placeholderERA != null)
    {
      placeholderERA.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "sessionToken" + ":" + getSessionToken()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "eRA = "+(getERA()!=null?Integer.toHexString(System.identityHashCode(getERA())):"null");
  }
}