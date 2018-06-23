package com.shaopeng.city;

import java.util.Comparator;

/**
 * Created by zhang on 16/7/4.
 */
public class PinyinComparator implements Comparator<CityBean> {

	public int compare(CityBean o1, CityBean o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
