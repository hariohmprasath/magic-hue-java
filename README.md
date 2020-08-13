# Home automation with Nexlux LED & MagicHue lights in JAVA
This program supports various controlling aspects of this Wifi enabled LED lights.

##Supported operations
<table>
<thead>
<tr>
<th>Operations</th>
<th>Sample values</th>
</tr>
</thead>
<tbody>
<tr>
<td>Turn On</td>
<td></td>
</tr>
<tr>
<td>Turn Off</td>
<td></td>
</tr>
<tr>
<td>Set color (RGB)</td>
<td><code>100,100,100</code></td>
</tr>
<tr>
<td>Set Warm</td>
<td><code>150</code></td>
</tr>
<tr>
<td>Set Cool</td>
<td><code>200</code></td>
</tr>
<tr>
<td>Get Status</td>
<td>Output: <code>{status=OFF, rgb=0,0,0, warm=0}</code></td>
</tr>
</tbody>
</table>

<i><b><u>Note:</u></b> IP address of the LED light is passed in as an argument for all these operations. </i>

## Hardware
* Nexlux LED with Wifi controller - <a href="https://www.amazon.com/gp/product/B0722VLVRR/ref=ppx_yo_dt_b_asin_title_o07_s00?ie=UTF8&psc=1">Click here</a>
* Govee Smart Wifi LED Magic hue - <a href="https://www.amazon.com/Govee-Brighter-Million-Controlled-Kitchen/dp/B07N1CMGQQ/ref=sr_1_1?dchild=1&keywords=magic+hue+led&qid=1597272104&s=home-garden&sr=1-1"> Click here </a>

## Implementation
* Java sockets are used to connect send request to the IP address assinged to these LED lights
* Here are the HEX codes sent across for each operations:
<table>
<thead>
<tr>
<th>Operations</th>
<th>Hex values</th>
</tr>
</thead>
<tbody>
<tr>
<td>Turn On</td>
<td><code>71:23:0f</code></td>
</tr>
<tr>
<td>Turn Off</td>
<td><code>71:24:0f</code></td>
</tr>
<tr>
<td>Set color (RGB)</td>
<td><code>RGB converted to hex</code></td>
</tr>
<tr>
<td>Set Warm</td>
<td><code>31:00:00:00:<b>Warm_value</b>:0f:0f</code></td>
</tr>
<tr>
<td>Set Cool</td>
<td><code>31:00:00:00:00:<b>Cold_value</b>:0f</code></td>
</tr>
<tr>
<td>Get Status</td>
<td><code>81:8a:8b:96</code></td>
</tr>
</tbody>
</table>
<i><u><b>Note:</b></u> Code implementation can found inside "MagicHeuHelper.java"</i>

## Maven
<code>&lt;repository&gt;<br>&nbsp;&nbsp;&nbsp;&lt;id>repsy&lt;/id&gt;<br>
  &nbsp;&nbsp;&nbsp;&lt;name&gt;repsy&lt;/name&gt;<br>
  &nbsp;&nbsp;&nbsp;&lt;url&gt;https:\//repo.repsy.io/mvn/hariohmprasath/magic-hue&lt;/url&gt;
&lt;/repository&gt;
</code>

<code>&lt;dependency&gt;<br>
&nbsp;&nbsp;&nbsp;&lt;groupId&gt;org.home&lt;/groupId&gt;<br>
&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;magic-hue-java&lt;/artifactId&gt;<br>
&nbsp;&nbsp;&nbsp;&lt;version&gt;1.0-SNAPSHOT&lt;/version&gt;<br>
&lt;/dependency&gt;
</code>
    
## Code snippets
<table>
<thead>
<tr>
<th>Operations</th>
<th>Hex values</th>
</tr>
</thead>
<tbody>
<tr>
<td>Turn On</td>
<td><code>MagicHueHelper.turnOn(IP_ADDRESS)</code></td>
</tr>
<tr>
<td>Turn Off</td>
<td><code>MagicHueHelper.turnOff(IP_ADDRESS)</code></td>
</tr>
<tr>
<td>Set color (RGB)</td>
<td><code>MagicHueHelper.setRgb(IP_ADDRESS, "100,100,100")</code></td>
</tr>
<tr>
<td>Set Warm</td>
<td><code>MagicHueHelper.setWarm(IP_ADDRESS, "150")</code></td>
</tr>
<tr>
<td>Set Cool</td>
<td><code>MagicHueHelper.setCool(IP_ADDRESS, "200")</code></td>
</tr>
<tr>
<td>Get Status</td>
<td><code>MagicHueHelper.getStatus(IP_ADDRESS)</code></td>
</tr>
</tbody>
</table>

## Credits
Thanks to excellent documentation by @kirillsimin part of his contribution in (https://github.com/kirillsimin/magichue). I was able to create a java version for this.
