package org.trafficlights.simulator;

import org.trafficlights.rules.*;

// inject strategies from ".tlc" files

//##markup##"strategies"
//##begin##"strategies"
class BusinessDayNight extends TrafficLightStrategy {
		public BusinessDayNight () {}

		public bool start() {
			return time() > (17*60 + 30)*60;
	}

	public int executeRules() {
		int iTriggeredRules = 0;
		if (bActive_) {
			if (executeRule0()) ++iTriggeredRules;
			if (executeRule1()) ++iTriggeredRules;
			if (executeRule2()) ++iTriggeredRules;
			if (executeRule3()) ++iTriggeredRules;
		}
		return iTriggeredRules;
	}
	
	private bool executeRule0() {
		if (vehicles_hour("Boulevard des Capuciens", "place_opera", "c->s") < 400 == false) {
				return false;
		}
	  {
	  int[] durations = {65, 120, -1};
	      setDuration("Rue Scribe", "scribe", "c->s", durations);
	    }
		return true;
	}
	
	private bool executeRule1() {
		if (vehicles_hour("Rue de la Paix", "rue_paix", "s->c") > 300 == false) {
				return false;
		}
	  activateStrategy("RivoliLowDensity");
		return true;
	}
	
	private bool executeRule2() {
		if (vehicles_hour("Rue auber", "auber", "c->s") > 500 == false) {
				return false;
		}
	  bActive_ = false;
		return true;
	}
	
	private bool executeRule3() {
		if (time() > (22*60 + 30)*60 == false) {
				return false;
		}
	  bActive_ = false;
		return true;
	}
	
}

//##end##"strategies"


/* The DSL code inside ##data## tags isn't valid Java code.
   So enclose it in comments.
//##markup##"DSL: trafficlight"
//##data##
strategy BusinessDayMorning {
  start time() < (6*60 + 30)*60; // activate rule before 6:30

  vehicles_hour("Avenue de l'Opera"->place_opera) > 800
    => duration("Boulevard des Italiens"->place_opera, 0min50/1min10);
  vehicles_hour("Boulevard des Capuciens"->place_opera) > 700
    => duration(scribe->"Rue scribe", 1min05/1min30);
  time() > (9*60 + 30)*60 => desactivate;
}
//##data##
//##begin##"DSL: trafficlight"
*/
class BusinessDayMorning extends TrafficLightStrategy {
		public BusinessDayMorning () {}

		public bool start() {
			return time() < (6*60 + 30)*60;
	}

	public int executeRules() {
		int iTriggeredRules = 0;
		if (bActive_) {
			if (executeRule0()) ++iTriggeredRules;
			if (executeRule1()) ++iTriggeredRules;
			if (executeRule2()) ++iTriggeredRules;
		}
		return iTriggeredRules;
	}
	
	private bool executeRule0() {
		if (vehicles_hour("Avenue de l'Opera", "place_opera", "s->c") > 800 == false) {
				return false;
		}
	  {
	  int[] durations = {50, 70, -1};
	      setDuration("Boulevard des Italiens", "place_opera", "s->c", durations);
	    }
		return true;
	}
	
	private bool executeRule1() {
		if (vehicles_hour("Boulevard des Capuciens", "place_opera", "s->c") > 700 == false) {
				return false;
		}
	  {
	  int[] durations = {65, 90, -1};
	      setDuration("Rue scribe", "scribe", "c->s", durations);
	    }
		return true;
	}
	
	private bool executeRule2() {
		if (time() > (9*60 + 30)*60 == false) {
				return false;
		}
	  bActive_ = false;
		return true;
	}
	
}

/*
//##end##"DSL: trafficlight"
*/

public class Simulator {
  // this class is implemented by hand
}
