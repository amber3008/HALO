#Author: ax907v@att.com
@Hallo_Amend_step.feature
Feature: To Amend the order


@Hallo_Amend
  Scenario Outline: To create Halo Amend Order
    Given Env<env> is UP for the Halo Amend Order
     When Amend  is pushed <OrderActionID>
     Then Amend Order is CREATED in OCX.
     Then Push UpdateOAAdditionalInfo request <env><OrderActionID>
     Then Submit the order.
    
    
    Examples:
    | env   | OrderActionID  |
    | "ST3" |  "202226312"   |

    