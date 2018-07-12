package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
*
* @author Diego Munhoz
*/
public final class DataUtils {

	public static SimpleDateFormat dataFormatacao = null;
	private static Date dataAuxiliar = null;
	private static SimpleDateFormat dateFormat = null;
	private static StringBuffer stringBuffer = null;

	public static Date horasHighValues(Date date) {
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append(" - 23.59.59.999");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date horasLowValues(Date date) {
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append(" - 00.00.00.000");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date diaHighValues(Date date) {
		dataAuxiliar = new Date();
		Calendar calendar = null;
		try {
			dateFormat = new SimpleDateFormat("yyyy");
			Integer ano = Integer.parseInt(dateFormat.format(date));

			dateFormat = new SimpleDateFormat("MM");
			Integer mes = Integer.parseInt(dateFormat.format(date));

			calendar = new GregorianCalendar(ano, (mes - 1), 1);

		} catch (NumberFormatException e) {
		}

		dateFormat = new SimpleDateFormat("yyyy/MM/");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append(String.valueOf(calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH)));
		stringBuffer.append(" - 23.59.59.999");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date diaLowValues(Date date) {
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy/MM/");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append("01 - 00.00.00.000");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date mesHighValues(Date date) {
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append("/12/31 - 23.59.59.999");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date mesLowValues(Date date) {
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append("/01/01 - 00.00.00.000");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date segundosHighValues(Date date) {
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append(".59.999");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date segundosLowValues(Date date) {
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append(".00.000");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date minutosHighValues(Date date) {
		if(date==null){
			return null;
		}
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append(" - 23.59.59.999");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static Date minutosLowValues(Date date) {
		if(date==null){
			return null;
		}
		dataAuxiliar = new Date();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		stringBuffer = new StringBuffer();
		stringBuffer.append(dateFormat.format(date));
		stringBuffer.append(" - 00.00.00.000");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		try {
			dataAuxiliar.setTime(dateFormat.parse(stringBuffer.toString())
					.getTime());
		} catch (ParseException e) {
		}
		return dataAuxiliar;
	}

	public static boolean valida_dd_MM_yyyy(String data) {
		SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		FORMAT.setLenient(false);

		try {
			FORMAT.parse(data);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean valida_dd_MM_yyyy_HH_mm(String data) {
		SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		FORMAT.setLenient(false);

		try {
			FORMAT.parse(data);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String printDate(Date data) {
		dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH.mm.ss.SSS");
		return String.valueOf(dateFormat.format(data));
	}

}