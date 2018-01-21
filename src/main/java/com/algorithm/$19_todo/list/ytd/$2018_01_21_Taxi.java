package com.algorithm.$19_todo.list.ytd;

import com.algorithm.$8_annotation.single.ann.GuardedBy;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */
public class $2018_01_21_Taxi {

    @GuardedBy("this")
    private Point destination, location;

    public $2018_01_21_Taxi(Dispather dispather) {
        this.dispather = dispather;
    }

    public synchronized Point getPosition() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination)) {
            dispather.notifyAvailable(this);
        }

    }

    private final Dispather dispather;

    class Dispather {
        @GuardedBy("this")
        private final Set<$2018_01_21_Taxi> taxis;
        @GuardedBy("this")
        private final Set<$2018_01_21_Taxi> availableTaxis;

        public Dispather(Set<$2018_01_21_Taxi> taxis, Set<$2018_01_21_Taxi> availableTaxis) {
            this.taxis = taxis;
            this.availableTaxis = availableTaxis;
        }

        public synchronized void notifyAvailable($2018_01_21_Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public synchronized Image getImage() {
            Image image = new Image();
            for ($2018_01_21_Taxi taxi : taxis) {
                image.drawMarker(taxi.location);
            }
            return image;
        }


    }


    /**
     * ========================
     */
    class Point {
        private final Long longitude;
        private final Long latitude;

        public Point(Long longitude, Long latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }
    }

    class Image {
        public void drawMarker(Point location) {
            System.out.println("Image.drawMarker");
        }
    }

}
