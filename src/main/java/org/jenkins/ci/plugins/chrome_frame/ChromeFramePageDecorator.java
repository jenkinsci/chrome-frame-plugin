/*
 * The MIT License
 * 
 * Copyright (c) 2011, Jesse Farinacci
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkins.ci.plugins.chrome_frame;

import hudson.Extension;
import hudson.Util;
import hudson.model.PageDecorator;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * The <a href="http://wiki.jenkins-ci.org/display/JENKINS/Chrome+Frame+Plugin">
 * Chrome Frame Plugin</a> provides <a
 * href="http://code.google.com/chrome/chromeframe/">Google Chrome Frame</a>
 * request for all pages.
 * 
 * @author <a href="mailto:jieryn@gmail.com">Jesse Farinacci</a>
 */
@Extension
public final class ChromeFramePageDecorator extends PageDecorator
{
  /**
   * The default value for {@link #sendHeader}.
   */
  protected static final boolean DEFAULT_SEND_HEADER = true;

  /**
   * The default value for {@link #sendMeta}.
   */
  protected static final boolean DEFAULT_SEND_META   = true;

  /**
   * The default value for {@link #sendScript}.
   */
  protected static final boolean DEFAULT_SEND_SCRIPT = true;

  /**
   * The default value for {@link #compatible}.
   */
  protected static final String  DEFAULT_COMPATIBLE  = "chrome=1";

  /**
   * Whether or not to send the HTTP header.
   */
  private boolean                sendHeader;

  /**
   * Whether or not to send the HTML meta tag.
   */
  private boolean                sendMeta;

  /**
   * Whether or not to send the Chrome Frame "Install Now!" javascript.
   */
  private boolean                sendScript;

  /**
   * What to send to the client, both in the header and the meta, for Chrome
   * Frame compatibility.
   */
  private String                 compatible;

  /**
   * Create a default Chrome Frame {@link PageDecorator}.
   */
  public ChromeFramePageDecorator()
  {
    this(DEFAULT_SEND_HEADER, DEFAULT_SEND_META, DEFAULT_SEND_SCRIPT,
        DEFAULT_COMPATIBLE);
  }

  /**
   * Create a Chrome Frame {@link PageDecorator} with the specified
   * configuration.
   */
  @DataBoundConstructor
  public ChromeFramePageDecorator(final boolean sendHeader,
      final boolean sendMeta, final boolean sendScript, final String compatible)
  {
    super();
    load();
    this.sendHeader = sendHeader;
    this.sendMeta = sendMeta;
    this.sendScript = sendScript;
    this.compatible = Util.fixEmpty(compatible);
  }

  @Override
  public String getDisplayName()
  {
    return Messages.Chrome_Frame_Plugin_DisplayName();
  }

  @Override
  public boolean configure(final StaplerRequest request, final JSONObject json)
      throws FormException
  {
    request.bindJSON(this, json);
    save();
    return true;
  }

  public boolean isSendHeader()
  {
    return sendHeader;
  }

  public void setSendHeader(final boolean sendHeader)
  {
    this.sendHeader = sendHeader;
  }

  public boolean isSendMeta()
  {
    return sendMeta;
  }

  public void setSendMeta(final boolean sendMeta)
  {
    this.sendMeta = sendMeta;
  }

  public boolean isSendScript()
  {
    return sendScript;
  }

  public void setSendScript(final boolean sendScript)
  {
    this.sendScript = sendScript;
  }

  public String getCompatible()
  {
    if (StringUtils.isEmpty(compatible))
    {
      return DEFAULT_COMPATIBLE;
    }

    return compatible;
  }

  public void setCompatible(final String compatible)
  {
    if (StringUtils.isEmpty(compatible))
    {
      this.compatible = DEFAULT_COMPATIBLE;
    }

    else
    {
      this.compatible = compatible;
    }
  }
}
