package net.penyo.fuckjetbrains.controller;

import jakarta.servlet.http.HttpServletResponse;
import net.penyo.fuckjetbrains.util.LicenseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rpc")
public class LicenseController {

    @RequestMapping("/obtainTicket.action")
    public String obtainTicket(String salt, String userName) throws Exception {
        String content = "<ObtainTicketResponse>" +
                "<message></message>" +
                "<prolongationPeriod>607875500</prolongationPeriod>" +
                "<responseCode>OK</responseCode>" +
                "<salt>" + salt + "</salt>" +
                "<ticketId>1</ticketId>" +
                "<ticketProperties>licensee=" + userName + "    licenseType=0   </ticketProperties>" +
                "</ObtainTicketResponse>";
        return LicenseUtils.rsaSign(content);
    }

    @RequestMapping("/releaseTicket.action")
    public void releaseTicket(HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @RequestMapping("ping.action")
    public String ping(String salt) throws Exception {
        String content = "<PingResponse>" +
                "<message></message>" +
                "<responseCode>OK</responseCode>" +
                "<salt>" + salt + "</salt>" +
                "</PingResponse>";
        return LicenseUtils.rsaSign(content);
    }
}
