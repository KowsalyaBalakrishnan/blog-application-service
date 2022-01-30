package com.application.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class APIVersion {

    /*API Versioning
    * URL Path - /api/v1
    * Query Params - /api/info?version=1
    * Custom Headers - X-Version-API = 1
    * Content Negotiation - Accept headers = application/vnd.java-v1+json
    * */

    //@GetMapping("v1/info")
    //@GetMapping(value = "/info", params = {"version=1", "data=1"})
    //@GetMapping(value = "/info", headers = "API-VERSION=1")
    @GetMapping(value = "/info", produces = "app/version-v1+json")
    public String versionOne() {
        return "Version 1";
    }

    //@GetMapping("v2/info")
    //@GetMapping(value = "/info", params = {"version=2", "data=2"})
    //@GetMapping(value = "/info", headers = "API-VERSION=2")
    @GetMapping(value = "/info", produces = "app/version-v2+json")
    public String versionTwo() {
        return "Version 2";
    }
}
