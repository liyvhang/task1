package com.lyh;

import com.lyh.model.Banner;
import com.lyh.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class TestBanner {
    @Autowired
    BannerService bannerService;
    @Test
    public void testSelect(){
        Banner banner = bannerService.selectByPrimaryKey(1L);
        System.out.println(banner);
    }
    @Test
    public void testInsert(){
        Banner record = new Banner();
        record.setCover("图片");
        record.setUpdateBy("哪吒");
        record.setUrl("http://web-ssm.com/1.jpg");
        record.setStatus((short) 1);
        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        bannerService.insertSelective(record);
        System.out.println(record.getId());
    }

    @Test
    public void testDelete(){
        bannerService.deleteByPrimaryKey((long) 1);
    }
    @Test
    public void testUpdate(){
        Banner record = new Banner();
        record.setCover("图片");
        record.setUpdateBy("吕布");
        record.setUrl("http://web-ssm.com/1.jpg");
        record.setStatus((short) 1);
        record.setId((long) 2);
//        record.setCreateAt(System.currentTimeMillis());
        record.setUpdateAt(System.currentTimeMillis());
        bannerService.updateByPrimaryKeySelective(record);
    }
    @Test
    public void testFindBannerSelective(){
      List<Banner> banners =  bannerService.findBanner(null, null);
    for (Banner banner : banners){
        System.out.println(banner);
    }
    }
}
