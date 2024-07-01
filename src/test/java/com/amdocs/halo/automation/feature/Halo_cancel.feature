#Author: ak7547@att.com
@Halo_cancel.feature
Feature: To Cancel HALO Order


@HALO_Cancel
  Scenario Outline: HALO 
    Given Env is up <Env> for the HALO CANCEL
    When OrderCancel is pushed <orderID><url><oaid>
    Then order is created
    
    
    Examples: 
    | Env   |orderID      |url                                 |oaid |
    | "ST3" | "202226311" |"http://zltv9973.vci.att.com:41400" |"/rp-webapp-9/ordering/Cancel"|
 