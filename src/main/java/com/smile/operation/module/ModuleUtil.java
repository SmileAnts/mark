package com.smile.operation.module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.CallableStatement;

public class ModuleUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/smile";
	private static final String NAME = "root";
	private static final String PASS = "root";
	private String packagePath = "com.smile.entity"; // 指定实体生成所在包的路径
	// 表名以","分隔;若为"*"则生成所有表的entity文件
	private String tableNames = "*";

	private String authorName = "Smile"; // 作者名字
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String upFirstChar(String str) {
		if (str != null && str.length() > 0) {
			char[] ch = str.toCharArray();
			if (ch[0] >= 'a' && ch[0] <= 'z') {
				ch[0] = (char) (ch[0] - 32);
			}
			return new String(ch);
		}
		return null;
	}

	/**
	 * 获取jdbc连接
	 */
	private java.sql.Connection getJdbcCon() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, NAME, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭所有资源
	 */
	private void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connnection,
			CallableStatement callableStatement) {
		// 关闭结果集对象
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		// 关闭PreparedStatement对象
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		// 关闭CallableStatement 对象
		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		// 关闭Connection 对象
		if (connnection != null) {
			try {
				connnection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 生成类主题框架
	 */
	public String genFrame(List<FieldMeta> fmls, String tableName) {
		StringBuilder sb = new StringBuilder();
		// package空间
		sb.append("package " + this.packagePath + ";\r\n\r\n");
		// 判断是否导入工具包
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * 名   称：" + tableName + "\r\n");
		sb.append(" * 描   述：\r\n");
		sb.append(" * 作   者：" + this.authorName + "\r\n");
		sb.append(" * 时   间：" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "\r\n");
		sb.append(" * --------------------------------------------------" + "\r\n");
		sb.append(" * 修改历史" + "\r\n");
		sb.append(" * 序号    日期    修改人     修改原因 " + "\r\n");
		sb.append(" * 1" + "\r\n");
		sb.append(" * **************************************************" + "\r\n");
		sb.append(" */\r\n");
		// 实体部分
		sb.append("public class " + upFirstChar(tableName) + "{\r\n");
		// 字段部分
		genAttrs(sb, fmls);
		// 方法部分
		genMethods(sb, fmls);
		sb.append("}\r\n");
		return new String(sb);
	}

	/**
	 * 功能：生成所有属性
	 */
	private void genAttrs(StringBuilder sb, List<FieldMeta> fmls) {
		for (int i = 0; i < fmls.size(); i++) {
			sb.append("\t");
			sb.append("private " + fmls.get(i).getFieldDataType() + " " + fmls.get(i).getFieldName() + ";");
			sb.append("\t/*" + fmls.get(i).getFieldComment() + "\tlen: " + fmls.get(i).getFieldLength() + "*/\r\n");
		}
		sb.append("\r\n");
	}

	/**
	 * 功能：生成所有方法
	 */
	private void genMethods(StringBuilder sb, List<FieldMeta> fmls) {
		for (int i = 0; i < fmls.size(); i++) {
			sb.append("\tpublic void set" + upFirstChar(fmls.get(i).getFieldName()) + "(" + fmls.get(i).getFieldDataType()
					+ " " + fmls.get(i).getFieldName() + "){\r\n");
			sb.append("\t\tthis." + fmls.get(i).getFieldName() + "=" + fmls.get(i).getFieldName() + ";\r\n");
			sb.append("\t}\r\n");
			sb.append(
					"\tpublic " + fmls.get(i).getFieldDataType() + " get" + upFirstChar(fmls.get(i).getFieldName()) + "(){\r\n");
			sb.append("\t\treturn " + fmls.get(i).getFieldName() + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\r\n");
		}
		sb.append("");
	}

	/**
	 * 写入文件
	 */
	private void wirteToFile(String content, String packagePath, String tableName) {
		PrintWriter pw = null;
		BufferedWriter bw = null;
		File dir = new File(""); // 根据空文件获取项目路径
		// entity文件父文件夹全路径
		String dirpath = dir.getAbsolutePath() + "\\src\\" + packagePath.replace(".", "\\");
		File parentDir = new File(dirpath);
		if (!parentDir.exists()) { // 判断是否存在该路径
			parentDir.mkdirs(); // 不存在则创建
		}
		String filePath = dirpath + "\\" + upFirstChar(tableName) + ".java"; // java文件路径
		File file = new File(filePath);
		try {
			if (!file.exists()) { // 判断是否存在java文件
				file.createNewFile(); // 不存在则创建
			} else {
				System.out.print("表     " + tableName + "   的entity实体已存在于:");
			}
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8")); // OutputStreamWriter为了解决乱码
			bw = new BufferedWriter(pw);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (pw != null) {
					pw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(filePath);
	}

	// 获取所有表名
	private String[] getAllTables() {
		List<String> stringList = new ArrayList<String>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = getJdbcCon();
		String sql = "show tables";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				stringList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, ps, con, null);
		}
		return stringList.toArray(new String[stringList.size()]);
	}

	public ModuleUtil() {

		String[] tables = null;
		if (this.tableNames == "*") {
			tables = getAllTables();
		} else {
			tables = this.tableNames.split(",");
		}
		for (int i = 0; i < tables.length; i++) {
			new ModuleUtil(tables[i]);
			f_util = false;
			f_sql = false;
		}
	}

	public ModuleUtil(String tableName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DatabaseMetaData dbmd = null;
		List<FieldMeta> fmls = new ArrayList<FieldMeta>();
		@SuppressWarnings("unused")
		String sql = "select * from " + tableName;
		try {
			con = getJdbcCon();
			dbmd = con.getMetaData();
			rs = dbmd.getColumns(con.getCatalog(), null, tableName, null);
			while (rs.next()) {
				FieldMeta fm = new FieldMeta();
				fm.setFieldName(rs.getString("COLUMN_NAME"));
				if (rs.getString("TYPE_NAME").equalsIgnoreCase("datetime")
						|| rs.getString("TYPE_NAME").equalsIgnoreCase("date")) {
					f_util = true;
				}
				if (rs.getString("TYPE_NAME").equalsIgnoreCase("image") || rs.getString("TYPE_NAME").equalsIgnoreCase("text")) {
					f_sql = true;
				}
				fm.setFieldDataType(rs.getString("TYPE_NAME"));
				fm.setFieldComment(rs.getString("REMARKS").replace("\r\n", "  ")); // 注释中的换行改为空格
				fmls.add(fm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				closeAll(rs, ps, con, null);
			}
		}
		// 生成内容
		String content = genFrame(fmls, tableName);
		// System.out.println(content);
		// 写入到文件
		wirteToFile(content, this.packagePath, tableName);
	}

	public static void main(String[] args) {
		new ModuleUtil();
	}
}