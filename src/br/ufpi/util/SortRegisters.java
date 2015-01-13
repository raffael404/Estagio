package br.ufpi.util;

import java.util.Collections;
import java.util.List;

import br.ufpi.model.Register;

/**
 * @author Rafael
 *
 */
public class SortRegisters {
	
	/**
	 * @param list
	 * @param l
	 * @param r
	 */
	public static void quickSort(List<Register> list, int l, int r){
		int i = l, j = r;
		int pivo = list.get(l + (r-l)/2).getId();
		
		while(i <= j){
			while(list.get(i).getId() < pivo){
				i++;
			}
			while (list.get(j).getId() > pivo) {
				j--;
			}
			if(i <= j){
				Collections.swap(list, i, j);
				i++;
				j--;
			}
		}
		if(l < j)
			quickSort(list, l, j);
		if(i < r)
			quickSort(list, i, r);
	}

}
