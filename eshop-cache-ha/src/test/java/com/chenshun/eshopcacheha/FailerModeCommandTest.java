package com.chenshun.eshopcacheha;

import com.chenshun.eshopcacheha.hystrix.command.FailerModeCommand;
import lombok.extern.slf4j.Slf4j;

/**
 * User: mew <p />
 * Time: 18/5/23 16:36  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
public class FailerModeCommandTest {

    public static void main(String[] args) {
        try {
            FailerModeCommand failerModeCommand = new FailerModeCommand(true);
            log.debug(failerModeCommand.execute().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
