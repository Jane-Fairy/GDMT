package com.controller;

import com.entity.AnnInfo;
import com.entity.ResultBean;
import com.service.serviceImpl.AnnInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/annInfo")
public class AnnInfoController {

    @Autowired
    AnnInfoServiceImpl annInfoService;

    /**
     * 查询所有资讯和公告
     * @return 所有资讯和公告
     */
    @RequestMapping(value = "/annAndInfo")
    public ResultBean getAllAnnAndInfo(){
        try {
            return new ResultBean(200,"查询资讯和公告成功",annInfoService.getAllAnnInfo());

        }catch (Exception e){
            return new ResultBean(400,"查询资讯和公告失败");
        }
    }

    /**
     * 查询所有资讯
     * @return 所有资讯
     */
    @RequestMapping(value = "/allAnn",method = RequestMethod.GET)
    public ResultBean getAllAnn(){
        try {
            return new ResultBean(200,"查询资讯成功",annInfoService.getAllAnno());
        }catch (Exception e){
            return new ResultBean(400,"查询资讯失败");
        }
    }

    /**
     * 查询所有公告
     * @return 所有公告
     */
    @RequestMapping(value = "/allInfo",method = RequestMethod.GET)
    public ResultBean getAllAllInfo(){
        try {
            return new ResultBean(200,"查询公告成功",annInfoService.getAllInfo());
        }catch (Exception e){
            return new ResultBean(400,"查询公告失败");
        }
    }

    /**
     * 根据id删除公告或资讯
     * @param annId 资讯或公告id
     * @return 删除结果
     */
    @RequestMapping(value = "/deleteAnnInfo/{annId}",method = RequestMethod.GET)
    public ResultBean deleteInfoById(@PathVariable("annId")int annId){
        try {
            return new ResultBean(200,"删除成功",annInfoService.deleteInfoById(annId));
        }catch (Exception e){
            return new ResultBean(400,"删除失败");
        }
    }

    /**
     *根据id修改公告或资讯
     * @param annInfo 资讯或公告对象
      * @return
     */
    @RequestMapping(value = "/UpdateAnnInfo",method = RequestMethod.GET)
    public ResultBean updateInfoById(AnnInfo annInfo){
        try {
            return new ResultBean(200,"修改成功",annInfoService.updateInfoById(annInfo));
        }catch (Exception e){
            return new ResultBean(400,"修改失败");
        }}


    @RequestMapping(value = "/addAnnInfo",method = RequestMethod.POST)
    public ResultBean addAnnInfo(@RequestBody AnnInfo annInfo){
        try {
            return new ResultBean(200,"新增成功",annInfoService.insertInfo(annInfo));
        }catch (Exception e){
            System.out.println(e);
            return new ResultBean(400,"新增失败");
        }
    }

    @RequestMapping(value = "/selectAnnOrInfo", method = RequestMethod.GET)
    public ResultBean selectAnnOrInfo(HttpServletRequest request){
        try {
            String annId = request.getParameter("annId");

            return new ResultBean(200,"查询成功",annInfoService.SeleteAnnOrInfoById(Integer.parseInt(annId)));
        }catch (Exception e){
            System.out.println(e);
            return new ResultBean(400,"查询失败");
        }
    }
}
