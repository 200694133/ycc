package com.Ycc.Calculator.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 算术工具类（使用逆波兰表达式算法）
 * 
 * @author YY
 * 
 */
public class MathUtil {

	private static final Map<String, Integer> ops = new HashMap<String, Integer>();
	private static Stack<String> stack;
	private static List<String> list;

	static {
		ops.put("+", 10);
		ops.put("-", 10);
		ops.put("*", 20);
		ops.put("/", 20);
		ops.put("%", 20);
		ops.put("(", 100);
		ops.put(")", 100);
	}

	/**
	 * 混合运算
	 * 
	 * @param str
	 *            需要计算的字符串
	 * @return
	 */
	public static String mixOperation(String str) {
		stack = new Stack<String>();
		list = new ArrayList<String>();
		List<String> ss = new ArrayList<String>();

		setListVaule(str, ss);

		for (String s : ss) {
			if (isSign(s)) {
				dealSign(s);
			} else {
				dealVar(s);
			}
		}
		while (stack.size() > 0) {
			list.add(stack.pop());
		}
		System.out.println(list);
		return String.valueOf(getResult());
	}

	/**
	 * 作用：将String算式中的数字和运算符分离
	 * 
	 * @param str
	 *            需要计算的算式
	 * @param ss
	 *            合并数字
	 */
	private static void setListVaule(String str, List<String> ss) {
		char[] a = str.toCharArray();
		String temp = "";
		for (char tempChar : a) {
			String c = String.valueOf(tempChar);
			if (c.equals("+") || c.equals("-") || c.equals("*")
					|| c.equals("/")) {
				ss.add(temp);
				ss.add(c);
				temp = "";
			} else {
				temp += c;
			}
		}
		if (!temp.equals(""))
			ss.add(temp);
	}

	/**
	 * 作用：判断当前是不是符号
	 * 
	 * @param sign1
	 *            当前数字或符号
	 * @return
	 */
	public static boolean isSign(String sign1) {
		Integer s = ops.get(sign1);
		if (s == null)
			return false;
		else
			return true;
	}

	/**
	 * 作用：如果当前为运算符就将运算符存入list中并取出栈中头元素装入list
	 * 
	 * @param s
	 *            运算符
	 */
	public static void dealSign(String s) {
		if (stack.size() == 0) {
			stack.push(s);
			return;
		}
		String ps = stack.pop();
		if (compare(s, ps) > 0 || ps.equals("(")) {
			if (s.equals(")")) {
				list.add(ps);
				while (stack.size() > 0) {
					ps = stack.pop();
					if (ps.equals("("))
						break;
					list.add(ps);
				}
			} else {
				stack.push(ps);
				stack.push(s);
			}
		} else {
			list.add(ps);
			dealSign(s);
		}
	}

	/**
	 * 作用：将需要运算的数字存入list中
	 * 
	 * @param s
	 *            数字
	 */
	private static void dealVar(String s) {
		list.add(s);
	}

	/**
	 * 作用：算出最后结果
	 * 
	 * @return
	 */
	private static Double getResult() {
		for (String s : list) {
			if (!isSign(s)) {
				stack.push(s);
				continue;
			}
			Object a = 0, b = 0;
			if (stack.size() > 0)
				b = stack.pop();
			if (stack.size() > 0)
				a = stack.pop();
			stack.push(cal(a, b, s) + "");
		}
		return Double.valueOf(stack.pop());
	}

	/**
	 * 作用：判断运算符优先级
	 * 
	 * @param sign1
	 * @param sign2
	 * @return
	 */
	public static int compare(String sign1, String sign2) {
		Integer p1 = ops.get(sign1);
		Integer p2 = ops.get(sign2);
		if (p1 == null)
			throw new IllegalArgumentException("符号：" + sign1 + "不存在！");
		if (p2 == null)
			throw new IllegalArgumentException("符号：" + sign2 + "不存在！");
		return p1 - p2;
	}

	/**
	 * 作用：算出结果
	 * 
	 * @param x
	 *            算术
	 * @param y
	 *            算术
	 * @param sign
	 *            运算符
	 * @return
	 */
	public static Object cal(Object x, Object y, String sign) {
		Double a = 0.0, b = 0.0;
		a = Double.valueOf(x + "");
		b = Double.valueOf(y + "");
		if (sign.equals("+"))
			return a + b;
		if (sign.equals("-"))
			return a - b;
		if (sign.equals("*"))
			return a * b;
		if (sign.equals("/"))
			return a / b;
		if (sign.equals("%"))
			return a % b;
		throw new IllegalArgumentException("操作符不合法！");
	}
}
