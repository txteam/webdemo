package com.tx.front4client.basicdata.controller;

import com.tx.core.util.StringUtils;
import com.tx.local.basicdata.model.District;
import com.tx.local.basicdata.model.DistrictTypeEnum;
import com.tx.local.basicdata.service.DistrictService;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("client.districtController")
@RequestMapping("/client/district")
public class DistrictController {

    @Resource
    private DistrictService districtService;

    /**
     * 查询District列表<br/>
     * @return List<District> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<District> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "type", required = false) DistrictTypeEnum type,
            @RequestParam(value = "level", required = false) Integer level,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();

        if (StringUtils.isEmpty(request.getFirst("parentIdLike"))) {
            params.put("parentId", request.getFirst("parentId"));
        } else {
            String parentIdLike = request.getFirst("parentIdLike");
            parentIdLike = parentIdLike.substring(0,
                    parentIdLike.indexOf("0000"));
            params.put("parentIdLike", parentIdLike);
        }

        params.put("countryId", request.getFirst("countryId"));
        params.put("provinceId", request.getFirst("provinceId"));
        params.put("cityId", request.getFirst("cityId"));

        params.put("name", request.getFirst("name"));
        params.put("fullName", request.getFirst("fullName"));
        params.put("code", request.getFirst("code"));

        params.put("type", type);
        params.put("level", level);
        params.put("maxLevel", request.getFirst("maxLevel"));

        List<District> resList = this.districtService.queryList(valid, params);

        return resList;
    }
}
