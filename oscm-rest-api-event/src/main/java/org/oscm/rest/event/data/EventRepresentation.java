package org.oscm.rest.event.data;

import org.oscm.internal.vo.VOGatheredEvent;
import org.oscm.rest.common.Representation;

import javax.ws.rs.WebApplicationException;

public class EventRepresentation extends Representation {

        private transient VOGatheredEvent vo;

        private long occurrenceTime;
        private String actor;
        private String eventId;
        private long multiplier = 1L;
        private String uniqueId;

        private String technicalServiceId;
        private String instanceId;
        private long subscriptionKey;

        public EventRepresentation() {
                this(new VOGatheredEvent());
        }

        public EventRepresentation(VOGatheredEvent ge) {
                vo = ge;
        }

        @Override
        public void validateContent() throws WebApplicationException {
        }

        @Override
        public void update() {
                vo.setActor(getActor());
                vo.setEventId(getEventId());
                vo.setMultiplier(getMultiplier());
                vo.setOccurrenceTime(getOccurrenceTime());
                vo.setUniqueId(getUniqueId());
        }

        @Override
        public void convert() {
                // not needed, only POST will be used
        }

        public long getOccurrenceTime() {
                return occurrenceTime;
        }

        public void setOccurrenceTime(long occurrenceTime) {
                this.occurrenceTime = occurrenceTime;
        }

        public String getActor() {
                return actor;
        }

        public void setActor(String actor) {
                this.actor = actor;
        }

        public String getEventId() {
                return eventId;
        }

        public void setEventId(String eventId) {
                this.eventId = eventId;
        }

        public long getMultiplier() {
                return multiplier;
        }

        public void setMultiplier(long multiplier) {
                this.multiplier = multiplier;
        }

        public String getUniqueId() {
                return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
                this.uniqueId = uniqueId;
        }

        public String getTechnicalServiceId() {
                return technicalServiceId;
        }

        public void setTechnicalServiceId(String technicalServiceId) {
                this.technicalServiceId = technicalServiceId;
        }

        public String getInstanceId() {
                return instanceId;
        }

        public void setInstanceId(String instanceId) {
                this.instanceId = instanceId;
        }

        public long getSubscriptionKey() {
                return subscriptionKey;
        }

        public void setSubscriptionKey(long subscriptionKey) {
                this.subscriptionKey = subscriptionKey;
        }

        public VOGatheredEvent getVO() {
                return vo;
        }

        public boolean isSubcriptionKeySet() {
                return getSubscriptionKey() > 0;
        }
}
