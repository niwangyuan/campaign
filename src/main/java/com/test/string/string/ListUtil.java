package com.test.string.string;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

public class ListUtil {

	/**
	 * 对List的多个属性按顺序进行排序
	 * 
	 * @param <T>
	 * @param reportList
	 * @param sortFiledNameList
	 * @param sort
	 *            0 正序 默认排序 1 逆序
	 * @return
	 */
	@SuppressWarnings("all")
	public static <T> List<T> sortListByMultiFields(List<T> list, final List<String> sortFiledNameList, Integer sort) {
		if (sortFiledNameList == null)
			return list;
		if (sort == null)
			sort = 0;
		ComparatorChain compChain = new ComparatorChain();
		Comparator mycmp = ComparableComparator.getInstance();
		mycmp = ComparatorUtils.reversedComparator(mycmp); // 逆序
		for (String sortFiledName : sortFiledNameList) {
			if (sort == 0) {// 默认顺序排序
				compChain.addComparator(new BeanComparator(sortFiledName));
			} else {// 倒叙顺序排序
				compChain.addComparator(new BeanComparator(sortFiledName, mycmp));
			}

		}
		
		
		/*
		T[] arrays = (T[]) list.toArray();
		Arrays.sort(arrays, compChain);
		return Arrays.asList(arrays);
		*/
		
		Collections.sort(list, compChain);
		return list;

		/**
		 * List<String> propertiyNames = new ArrayList<String>();
		 * propertiyNames.add("personGroupId");
		 * resultList=ListUtil.sortListByMultiFields(list, propertiyNames,
		 * sort);
		 */
	}

	/**
	 * list 去重
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeDuplicateWithOrder(List<T> list) {

		Set<T> set = new HashSet<T>();

		List<T> newList = new ArrayList<T>();

		for (Iterator<T> iter = list.iterator(); iter.hasNext();) {

			T element = iter.next();

			if (set.add(element))

				newList.add(element);

		}

		return newList;

	}

	/**
	 * <p>
	 * Checks if an array of Objects is not empty or not <code>null</code>.
	 * </p>
	 * 
	 * @param array
	 *            the array to test
	 * @return <code>true</code> if the array is not empty or not
	 *         <code>null</code>
	 * @since 2.5
	 */
	public static boolean isNotEmpty(Object[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of Objects is empty or <code>null</code>.
	 * </p>
	 * 
	 * @param array
	 *            the array to test
	 * @return <code>true</code> if the array is empty or <code>null</code>
	 * @since 2.1
	 */
	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 分割List
	 * 
	 * @param list
	 *            待分割的list
	 * @param pageSize
	 *            每段list的大小
	 * @return List<<List<T>>
	 */
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		List<List<T>> listArray = new ArrayList<List<T>>(); // 创建list数组
															// ,用来保存分割后的list

		if (list == null || list.size() == 0) {
			return listArray;
		}

		int listSize = list.size(); // list的大小
		int page = (listSize + (pageSize - 1)) / pageSize; // 页数

		for (int i = 0; i < page; i++) { // 按照数组大小遍历
			List<T> subList = new ArrayList<T>(); // 数组每一位放入一个分割后的list
			for (int j = 0; j < listSize; j++) { // 遍历待分割的list
				int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize; // 当前记录的页码(第几页)
				if (pageIndex == (i + 1)) { // 当前记录的页码等于要放入的页码时
					subList.add(list.get(j)); // 放入list中的元素到分割后的list(subList)
				}

				if ((j + 1) == ((j + 1) * pageSize)) { // 当放满一页时退出当前循环
					break;
				}
			}
			listArray.add(subList); // 将分割后的list放入对应的数组的位中
		}
		return listArray;
	}
	
	
    /**
     * 比较list内容是否相同。 同时为 null 表示相同。
     * @param a
     * @param b
     * @return 内容相同返回 true
     */
    public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
    	
    	//都是没数据
    	if(a == null && b == null){
    		return true;
    	}
    	
    	//其中一个有数据，一个没有
    	if((a == null && b != null) || (a != null && b == null)){
    		return false;
    	}
    	
        if(a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

	public static void main(String[] s) {

		List<String> strlist = new ArrayList<String>();
		for (int i = 0; i < 31; i++) {
			strlist.add("aa" + (i + 1));
		}
		List<List<String>> list = splitList(strlist, 30);

		List<String> resultList = new ArrayList<String>();
		int index = 1;
		for (List<String> strlist2 : list) {
			System.out.println(index++);
			System.out.println("----------------------------------");
			List<String> slist = new ArrayList<String>();
			for (String str : strlist2) {
				System.out.print(str + "\t");
				slist.add(str);
			}
			for (String a : slist) {
				resultList.add(a);
			}
			// slist.clear();
			// System.out.println("END");
			System.out.println();
		}

		System.out.println("----------------------------------");

		for (String a : resultList) {
			System.out.println(a);
		}
	}

}

