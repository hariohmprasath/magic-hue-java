# Home automation with Nexlux LED & MagicHue lights in JAVA
This program supports various controlling aspects of this Wifi enabled LED lights.

## Supported operations
<big>1. Turn On </big>
<br><big>2. Turn Off</big>
<br><big>3. Set color (RGB) - </big><i>Ex: 100,100,100</i>
<br><big>4. Set Warm - </big><i>Ex: 150</i>
<br><big>5. Set Cool - </big><i>Ex: 150</i>
<br><big>6. Get Status - </big><i>Sample output: {status=OFF, rgb=0,0,0, warm=0}</i>

<i><b><u>Note:</u></b> IP address of the LED light is passed in as an argument for all these operations. </i>

## Hardware
* Nexlux LED with Wifi controller - <a href="https://www.amazon.com/gp/product/B0722VLVRR/ref=ppx_yo_dt_b_asin_title_o07_s00?ie=UTF8&psc=1">Click here</a>
* Govee Smart Wifi LED Magic hue - <a href="https://www.amazon.com/Govee-Brighter-Million-Controlled-Kitchen/dp/B07N1CMGQQ/ref=sr_1_1?dchild=1&keywords=magic+hue+led&qid=1597272104&s=home-garden&sr=1-1"> Click here </a>

## Implementation
* Java sockets are used to connect send request to the IP address assinged to these LED lights
* Here are the HEX codes sent across for each operations:
<br><big>1. Turn On - </big><i>71:23:0f</i>
<br><big>2. Turn Off - </big><i>71:24:0f</i>
<br><big>3. Set Color - </big><i> RGB converted to hex </i>
<br><big>4. Set Warm - </big><i> 31:00:00:00:<b>Warm_value</b>:0f:0f </i>
<br><big>5. Set Cold - </big><i> 31:00:00:00:00:<b>Cold_value</b>:0f </i>
<br><big>6. Get Status - </big><i> 81:8a:8b:96</i>
<br><br><i><u><b>Note:</b></u> Code implementation can found inside "MagicHeuHelper.java"</i>

## Usage
<big>1. Turn On</big> - <i>MagicHueHelper.turnOn(IP_ADDRESS);</i>
<br><big>2. Turn Off - </big><i>MagicHueHelper.turnOff(IP_ADDRESS);</i>
<br><big>3. Set Color - </big><i>MagicHueHelper.setRgb(IP_ADDRESS, "100,100,100");</i>
<br><big>4. Set Warm - </big><i>MagicHueHelper.setWarm(IP_ADDRESS, "150");</i>
<br><big>5. Set Cold - </big><i>MagicHueHelper.setCool(IP_ADDRESS, "150");</i>
<br><big>6. Get Status - </big><i>MagicHueHelper.getStatus(IP_ADDRESS)</i>

## Credits
Thanks to excellent documentation by @kirillsimin part of his contribution in (https://github.com/kirillsimin/magichue). I was able to create a java version for this.
