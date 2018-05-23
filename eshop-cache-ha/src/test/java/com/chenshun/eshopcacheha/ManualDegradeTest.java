package com.chenshun.eshopcacheha;

import com.chenshun.eshopcacheha.degrade.IsDegrade;
import com.chenshun.eshopcacheha.hystrix.command.GetProductInfoFacadeCommand;
import lombok.extern.slf4j.Slf4j;

/**
 * User: mew <p />
 * Time: 18/5/23 17:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
public class ManualDegradeTest {

    public static void main(String[] args) {
        GetProductInfoFacadeCommand getProductInfoFacadeCommand1 = new GetProductInfoFacadeCommand(1L);
        log.debug(getProductInfoFacadeCommand1.execute().toString());

        IsDegrade.setDegrade(true);

        GetProductInfoFacadeCommand getProductInfoFacadeCommand2 = new GetProductInfoFacadeCommand(2L);
        log.debug(getProductInfoFacadeCommand2.execute().toString());
    }

}
