package com.wk.spider.ip.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wk.spider.ip.entity.Ips;
import com.wk.spider.ip.entity.IpInformation;

@Controller
public class ControllerIp {

	// 采集
	@RequestMapping("/getIp.action")
	public ModelAndView getOneIp(HttpServletRequest request, String sourceip, Integer tagId) {
		ModelAndView mv = new ModelAndView();
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String requestTime = sf.format(date);
		Ips ips = new Ips();
		ips.setRequestTime(requestTime);
		ips.setSourceip(sourceip);
		ips.setTagId(tagId);
		System.out.println(sourceip + ":" + tagId + ":" + requestTime);
		String jsOne = JSON.toJSONString(ips);
		System.out.println("jsOne" + jsOne);
		mv.addObject("jsOne", jsOne);
		mv.addObject("ips", ips);
		mv.setViewName("index");
		return mv;
	}

	// 写
	@RequestMapping("/writer.action")
	public ModelAndView setRealIp(HttpServletRequest request, String requestTime, String sourceip, String targetIp,
			Integer tagId) {
		ModelAndView mv = new ModelAndView();
		System.out.println("写的方法进来了");
		System.out.println("写的：" + sourceip + ":" + tagId + ":" + requestTime + ":" + targetIp);
		// 获取请求时间
		Date date = new Date();
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = sf1.format(date);
		
		 String ipsString = "{\"requestTime\":\""+requestTime+"\",\"sourceip\":\"" + 
		 sourceip + "\",\"tagId\":" + tagId +
		  ",\"targetIp\":\"" + targetIp+"\"}\r\n";
		 
		String path=request.getSession().getServletContext().getRealPath("/")+s1+".txt";
		System.out.println("ipsString:"+ipsString);
		//String path = "/" + s1 + ".txt";
		System.out.println("path1:" + path);
		Writer w = null;
		try {
			w = new FileWriter(path, true);
			w.write(ipsString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				w.flush();
				w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mv;
	}

	// 读
	@RequestMapping("/reader.action")
	public ModelAndView getDayIps(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		String json=null;
		System.out.println("读的方法进来了..");
		StringBuffer sb = new StringBuffer();
		List<Ips> list = new ArrayList<Ips>();
		// 获取请求时间
		Date date = new Date();
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = sf1.format(date);
		String path=request.getSession().getServletContext().getRealPath("/")+s1+".txt";
		//String path = "/" + s1 + ".txt";
		System.out.println("path2:" + path);
		File file=new File(path);
		//判断当日是否已经写入过ip
		if(!file.exists()) {
			mv.addObject("json", "当日无真实ip获取记录！");
			mv.setViewName("success");
			return mv;
		}
		Reader in = new FileReader(file);
		BufferedReader br = new BufferedReader(in);
		String stringIps = null;
		while ((stringIps = br.readLine()) != null) {
			System.out.println("stringIps:" + stringIps);
			Ips ips = JSON.parseObject(stringIps.trim(), Ips.class);
			System.out.println("ips:" + ips);
			list.add(ips);
		}
		// 初始化bb一个实例变量
		IpInformation b = new IpInformation();
		b.setData(list);
		b.setStatus(1);
		if (list.size() >= 0) {
			b.setStatus(1);
			b.setMessage("数据响应成功");
		} else {
			b.setStatus(0);
			b.setMessage("数据响应失败");
		}

		json = JSON.toJSONString(b);
		System.out.println("json数据:" + json);
		mv.addObject("json", json);
		mv.setViewName("success");
		return mv;
	}

}
