package ca.era.stride.controller;

import ca.era.stride.model.DataPoint;
import ca.era.stride.model.RecordingSession;
import ca.era.stride.model.Sensor;
import ca.era.stride.persistence.DataBuffer;

public class DataPointLogger {

    private final RecordingSession session;

    public DataPointLogger(RecordingSession session){
        this.session = session;
    }

    public void logDataPoint(float timeStamp, double x, double y, double z, Sensor sensor){
        DataPoint pt = session.addDataPoint(timeStamp, sensor);
        pt.addValue(x);
        pt.addValue(y);
        pt.addValue(z);
    }

    public void logDataPoint(float timeStamp, double value, Sensor sensor){
        DataPoint pt = session.addDataPoint(timeStamp, sensor);
        pt.addValue(value);
    }

    public void save() {
       //TODO implement local save thingy
        DataBuffer.saveDataLocally();

        //TODO save to db must be done properly
        DataBuffer.save(session);

        //TODO recording saved confirmation
    }
}
