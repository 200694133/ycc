package com.Ycc.Calculator.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * ���������ࣨʹ���沨�����ʽ�㷨��
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
	 * �������
	 * 
	 * @param str
	 *            ��Ҫ������ַ���
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
	 * ���ã���String��ʽ�е����ֺ����������
	 * 
	 * @param str
	 *            ��Ҫ�������ʽ
	 * @param ss
	 *            �ϲ�����
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
	 * ���ã��жϵ�ǰ�ǲ��Ƿ���
	 * 
	 * @param sign1
	 *            ��ǰ���ֻ����
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
	 * ���ã������ǰΪ������ͽ����������list�в�ȡ��ջ��ͷԪ��װ��list
	 * 
	 * @param s
	 *            �����
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
	 * ���ã�����Ҫ��������ִ���list��
	 * 
	 * @param s
	 *            ����
	 */
	private static void dealVar(String s) {
		list.add(s);
	}

	/**
	 * ���ã���������
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
	 * ���ã��ж���������ȼ�
	 * 
	 * @param sign1
	 * @param sign2
	 * @return
	 */
	public static int compare(String sign1, String sign2) {
		Integer p1 = ops.get(sign1);
		Integer p2 = ops.get(sign2);
		if (p1 == null)
			throw new IllegalArgumentException("���ţ�" + sign1 + "�����ڣ�");
		if (p2 == null)
			throw new IllegalArgumentException("���ţ�" + sign2 + "�����ڣ�");
		return p1 - p2;
	}

	/**
	 * ���ã�������
	 * 
	 * @param x
	 *            ����
	 * @param y
	 *            ����
	 * @param sign
	 *            �����
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
		throw new IllegalArgumentException("���������Ϸ���");
	}
}
