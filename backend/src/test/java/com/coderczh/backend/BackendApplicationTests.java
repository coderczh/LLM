package com.coderczh.backend;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.entity.UserInfo;
import io.github.yindz.random.RandomSource;
import io.github.yindz.random.constant.Province;
import io.github.yindz.random.source.PersonInfoSource;
import jakarta.annotation.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

	private static final String[] EMAIL_SUFFIX = {"gmail.com", "yahoo.com", "163.com", "126.com", "qq.com", "foxmail.com",
			"outlook.com", "hotmail.com", "icloud.com", "sina.com"};

	@Resource
	private StringEncryptor stringEncryptor;

	@Resource
	private UserInfoDao userInfoDao;

	@Test
	void randomUserInfo() {

	}

	private Map<String, String> getUserInfo(int random) {
		PersonInfoSource personInfoSource = RandomSource.personInfoSource();
		Map<String, String> userInfoMap = new HashMap<>();
		// 真实姓名
		String realName = random == 0 ? personInfoSource.randomMaleChineseName() : personInfoSource.randomFemaleChineseName();
		userInfoMap.put("realName", realName);
		// 昵称
		userInfoMap.put("nickName", personInfoSource.randomQQNickName());
		// 年龄
		int age = RandomUtil.randomInt(10, 70);
		userInfoMap.put("age", String.valueOf(age));
		// 性别
		userInfoMap.put("gender", random == 0 ? "男" : "女");
		// 身份证号
		Province[] provinces = Province.values();
		Province province = provinces[RandomUtil.randomInt(0, provinces.length)];
		String idCard = random == 0 ? personInfoSource.randomMaleIdCard(province, age) : personInfoSource.randomFemaleIdCard(province, age);
		userInfoMap.put("idCard", idCard);
		// 邮箱
		int emailIndex = RandomUtil.randomInt(0, EMAIL_SUFFIX.length);
		String email = RandomSource.internetSource().randomEmail(10, EMAIL_SUFFIX[emailIndex]);
		userInfoMap.put("email", email);
		// 地址
		String address = RandomSource.areaSource().randomAddress(province);
		userInfoMap.put("address", address);
		// 手机号
		String phoneNo = RandomSource.personInfoSource().randomChineseMobile();
		userInfoMap.put("phoneNo", phoneNo);
		// 账号
		userInfoMap.put("accountNo", personInfoSource.randomNickName(10));
		// 密码
		int passwordLength = RandomUtil.randomInt(6, 16);
		userInfoMap.put("password", personInfoSource.randomStrongPassword(passwordLength, false));
		// 创建日期
		LocalDate beginDate = LocalDate.of(2000, 1, 1);
		LocalDate endDate = LocalDate.of(2025, 12, 31);
		String createDate = RandomSource.dateTimeSource().randomDate(beginDate, endDate, "yyyy-MM-dd");
		userInfoMap.put("createDate", createDate);
		// 创建时间
		userInfoMap.put("createTime", getRandomTime());
		// 更新日期
		String updateDate = DateUtil
				.offsetDay(DateUtil.parse(createDate, "yyyy-MM-dd"), RandomUtil.randomInt(1, 1000))
				.toDateStr();
		userInfoMap.put("updateDate", updateDate);
		// 更新时间
		userInfoMap.put("updateTime", getRandomTime());
		return userInfoMap;
	}

	private String getRandomTime() {
		LocalDateTime localDateTime = RandomSource.dateTimeSource().randomTime(2025, 5, 25);
		return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	@Test
	public void getDecrypt() {
		PersonInfoSource personInfoSource = RandomSource.personInfoSource();
		List<UserInfo> userInfoList = new ArrayList<>();
		for (int i = 5001; i <= 10000; i++) {
			int passwordLength = RandomUtil.randomInt(6, 16);
			String password = personInfoSource.randomStrongPassword(passwordLength, false);
			UserInfo userInfo = new UserInfo();
			userInfo.setPassword(stringEncryptor.encrypt(password)).setId(i);
			userInfoList.add(userInfo);
			if (i % 1000 == 0) {
				System.out.println("=============================== 第" + i / 1000 + "轮插入数据开始 ===============================");
				userInfoDao.updateById(userInfoList);
				System.out.println("=============================== 第" + i / 1000 + "轮插入数据结束 ===============================");
			}
		}
	}

	@Test
	public void getPassword() {
		String decrypt = stringEncryptor.decrypt("4XjWmNVwMgMAp35SkFrnJDcEX6w2ciVmiTlH1hHexmaljNqMXWmmtxrjr/v377IO");
		System.out.println(decrypt);
	}
}
