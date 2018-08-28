package br.com.ifood.vehiclerouting.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
@ConfigurationProperties("vr.ga")
public class GAProperties {

    private boolean parallel;

    private int maxGenerations;

    private int maxPopulationSize;

    private float mutationRate;

    private float crossoverRate;

    @Autowired
    private ForkJoinPool pool;

    public static class Constraints {

        private Hard hard;
        private Soft soft;

        public static class Hard {

            private int chargeLimit;
            private double extraRestaurants;
            private double pickupTimeAhead;
            private double extraCharge;

            public int getChargeLimit() {
                return chargeLimit;
            }

            public void setChargeLimit(int chargeLimit) {
                this.chargeLimit = chargeLimit;
            }

            public double getExtraRestaurants() {
                return extraRestaurants;
            }

            public void setExtraRestaurants(double extraRestaurants) {
                this.extraRestaurants = extraRestaurants;
            }

            public double getPickupTimeAhead() {
                return pickupTimeAhead;
            }

            public void setPickupTimeAhead(double pickupTimeAhead) {
                this.pickupTimeAhead = pickupTimeAhead;
            }

            public double getExtraCharge() {
                return extraCharge;
            }

            public void setExtraCharge(double extraChargeOverThree) {
                this.extraCharge = extraChargeOverThree;
            }
        }

        public static class Soft {

            private double deliveryTimeDelayed;
            private double amountRiders;

            public double getDeliveryTimeDelayed() {
                return deliveryTimeDelayed;
            }

            public void setDeliveryTimeDelayed(double deliveryTimeDelayed) {
                this.deliveryTimeDelayed = deliveryTimeDelayed;
            }

            public double getAmountRiders() {
                return amountRiders;
            }

            public void setAmountRiders(double amountRiders) {
                this.amountRiders = amountRiders;
            }
        }

        public Hard getHard() {
            return hard;
        }

        public void setHard(Hard hard) {
            this.hard = hard;
        }

        public Soft getSoft() {
            return soft;
        }

        public void setSoft(Soft soft) {
            this.soft = soft;
        }

    }

    private Constraints constraints;

    public Constraints getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    public void setMaxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }

    public int getMaxPopulationSize() {
        return maxPopulationSize % 2 == 0 ? maxPopulationSize: maxPopulationSize+1;
    }

    public void setMaxPopulationSize(int maxPopulationSize) {
        this.maxPopulationSize = maxPopulationSize;
    }

    public float getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    public float getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(float crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public boolean isParallel() {
        return parallel;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    public ForkJoinPool getPool() {
        return pool;
    }

    public void setPool(ForkJoinPool pool) {
        this.pool = pool;
    }
}
