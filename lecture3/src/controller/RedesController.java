package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RedesController {

	private static String os() {
		String os = System.getProperty("os.name");
		if (os.contains("Linux")) {
			os += " " + System.getProperty("os.arch");
		}
		os += " " + System.getProperty("os.version");
		return os;
	}

	public static void ip() {
		String osName = os();

		if (osName.contains("Linux")) {
			try {
				Process proc = Runtime.getRuntime().exec("ip addr");
				processOutput(proc.getInputStream(), osName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (osName.contains("Windows")) {
			try {
				Process proc = Runtime.getRuntime().exec("ipconfig");
				processOutput(proc.getInputStream(), osName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void ping() {
		String osName = os();

		try {
			Process process;
			if (osName.contains("Windows")) {
				process = Runtime.getRuntime().exec("ping -n 10 www.google.com.br");
				pingAverageOutput(process);
			} else {
				process = Runtime.getRuntime().exec("ping -c 10 www.google.com.br");
				pingAverageOutput(process);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void processOutput(InputStream inputStream, String osName) {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
			Pattern adapterPattern = Pattern.compile("^\\s*(\\w+) .*$");
			Pattern ipv4Pattern = Pattern.compile(".*IPv4 Address\\s*: ([0-9.]+).*");

			buffer.lines().map(line -> {
				Matcher adapterMatcher = adapterPattern.matcher(line);
				Matcher ipv4Matcher = ipv4Pattern.matcher(line);

				String adapterName = adapterMatcher.matches() ? adapterMatcher.group(1) : null;
				String ipv4Address = ipv4Matcher.matches() ? ipv4Matcher.group(1) : null;

				return osName + "|" + (adapterName != null ? adapterName + ": " : "")
						+ (ipv4Address != null ? ipv4Address : "");
			}).filter(line -> !line.endsWith(": "))
					.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void pingAverageOutput(Process process) throws IOException {
		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String pingOutput = reader.lines().collect(Collectors.joining("\n"));

		Pattern avgTimePattern = Pattern.compile("Average = (\\d+)ms");
		Matcher avgTimeMatcher = avgTimePattern.matcher(pingOutput);

		if (avgTimeMatcher.find()) {
			String avgTime = avgTimeMatcher.group(1);
			System.out.println("Tempo m�dio de ping: " + avgTime + "ms");
		} else {
			System.out.println("N�o foi poss�vel obter o tempo m�dio de ping.");
		}

		reader.close();
	}
}
