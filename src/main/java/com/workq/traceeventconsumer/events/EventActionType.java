package com.workq.traceeventconsumer.events;

/**
 * This event represents a type of {@link ProcessTraceEvent}
 */
public enum EventActionType {

    None, BeforeProcessStarted, BeforeNodeTriggered, AfterNodeLeft, AfterProcessCompleted
}
