package com.chenshun.eshopcacheha.degrade;

/**
 * User: mew <p />
 * Time: 18/5/23 17:22  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class IsDegrade {

    private static boolean degrade = false;

    public static boolean isDegrade() {
        return degrade;
    }

    public static void setDegrade(boolean degrade) {
        IsDegrade.degrade = degrade;
    }

}
