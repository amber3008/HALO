#Author: md388b@att.com
@MTP_DISCONNECT
Feature: To create GAMMA PTP Order in OCX


@Test_GAMMA_MTP
  Scenario Outline: Create GAMMA MTP order in Env
    Given Env<eNv> is UP for   MTP  order Creation for 
     When CNOD of first UNI is pushed  to OCX  for PTP<eNv> <stateRegion1>
     And  CNOD of UNI_B is pushed to  OCX  for PTP<eNv> <stateRegion2>
     And  third CNOD of UNI_C is pushed to  OCX for PTP<eNv> <stateRegion3>
     And CNOD of MPT is  PUSHED to OCX for UNI_A and UNI_B and UNI_C<eNv> <stateRegion>
     Then PTP  OA is   CREATED in  OCX.
     And Push  notify  on  request for  MTP_DISCONNECT<eNv>
     And Push  Disconnect  request  for  MTP_DISCONNECT<eNv>
     Then  MTP_DISCONNECT  is  created  in OCX.
    
    
    Examples: 
    | eNv   |stateRegion1|stateRegion2|stateRegion2|
    | "ST4" |"O"|"Y"|"O"|


    