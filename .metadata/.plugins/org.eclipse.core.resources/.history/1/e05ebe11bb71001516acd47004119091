package com.thoughtriott.metaplay.data.repositories;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.RecordLabel;

public class RecordLabelRepositoryImpl implements RecordLabelRepositoryCustom {

	@Autowired
	RecordLabelRepository recordLabelRepository;
	
	//returns List<String> of each RecordLabel
	@Override
	public List<String> findAllAsListString() {
		List<RecordLabel> recordLabelList = recordLabelRepository.findAll();
		Iterator<RecordLabel> it = recordLabelList.iterator();
		List<String> recLblStrList = new LinkedList<String>();
		while(it.hasNext()) {
			RecordLabel rl = it.next();
			recLblStrList.add(rl.getName());
		}
		if(recordLabelList.size()==0) {
			System.out.println("The results list was empty.");
			recLblStrList.add("No Artists exist, add one!");
			recLblStrList.add("** New Record Label **");
			return recLblStrList;
		} else {
			recLblStrList.add("** New Record Label **");
			return recLblStrList;
		}
	}
	
	// return a string of all of the RecordLabels with a certain name
	@Override
	public String findRecordLabelsByNameToString(String name) {
		List<RecordLabel> recordLabelsList = recordLabelRepository.findRecordLabelByName(name);
		int indexCounter = 0;
		String recordLabelString = "";

		Iterator<RecordLabel> it = recordLabelsList.iterator();
		while (it.hasNext()) {
			RecordLabel currentRecordLabel = it.next();
			indexCounter++;
			if (recordLabelsList.size() == 1) {
				recordLabelString = "{" + currentRecordLabel.getName() + "}";
			} else if (indexCounter < recordLabelsList.size() && indexCounter != 1) {
				recordLabelString = recordLabelString + ", " + currentRecordLabel.getName();
			} else if (indexCounter == recordLabelsList.size()) {
				recordLabelString = recordLabelString + ", " + currentRecordLabel.getName() + "}";
			} else {
				recordLabelString = "{" + currentRecordLabel.getName();
			}
		}
		return recordLabelString;
	}


}
