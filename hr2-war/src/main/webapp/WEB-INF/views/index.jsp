<!DOCTYPE html>
<!--[if IE 8]>
		<html xmlns="http://www.w3.org/1999/xhtml" class="ie8" lang="en">
	<![endif]-->
<!--[if !(IE 8) ]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>HR Intranet &rsaquo; Log In</title>
<style type="text/css">
body,form .input
{
	font-family:"Open Sans","Helvetica Neue","Arial",sans
}

body.login.recover-password,body.login.recover-password-success
{
	height:auto;
	min-height:100%
}

#recovery
{
	padding-bottom:24px
}

.recover-password #login_error
{
	width:auto
}

#login_error ul
{
	list-style:square outside none;
	margin-left:15px
}

#login_error ol
{
	margin-left:25px
}

.message ol
{
	margin:10px 0 10px 20px
}

#login_error h2,#recovery h2
{
	padding-bottom:10px
}

#login_error h3
{
	padding-bottom:5px;
	margin-top:10px
}

.recover-password #recovery h2
{
	margin-bottom:5px
}

.recover-password-success #recovery h2
{
	margin-bottom:.5em
}

.recover-password #recovery p
{
	line-height:1.6;
	padding-bottom:10px;
	border-top:none
}

.recover-password-success #recovery p
{
	line-height:1.6;
	padding-bottom:1em
}

.recovery-stage
{
	margin:14px 0 27px
}

.login .recovery-stage input[type="text"]
{
	margin-bottom:5px
}

.recovery-ownership-message
{
	background-color:#fff;
	-webkit-box-shadow:0 1px 2px rgba(0,0,0,0.1);
	box-shadow:0 1px 2px rgba(0,0,0,0.1);
	padding:7px 15px 0;
	margin:1em 0;
	background:#ffffe0
}

.recovery-error-message
{
	display:none;
	background-color:#f1831e;
	-webkit-box-shadow:0 1px 2px rgba(0,0,0,0.1);
	box-shadow:0 1px 2px rgba(0,0,0,0.1);
	padding:7px 15px 0;
	margin:1em 0;
	background:#f1831e
}

.recover-password #recovery textarea
{
	width:100%;
	box-sizing:border-box;
	-moz-box-sizing:border-box;
	-webkit-box-sizing:border-box;
	-ms-box-sizing:border-box
}

.recover-password #recovery .recovery-ownership-title
{
	position:relative;
	padding-bottom:0
}

.recovery-ownership-help
{
	float:right;
	font-size:11px;
	margin-top:-10px;
	margin-bottom:5px
}

.recovery-ownership
{
	clear:both
}

.recovery-ownership-help
{
	margin:3px 0 0
}

.recovery-ownership-help-text
{
	display:none;
	position:absolute;
	top:25px;
	right:0;
	z-index:100;
	border:1px solid #e6db55;
	background-color:#ffffe0;
	width:250px;
	padding:8px;
	box-shadow:0 4px 10px -1px rgba(200,200,200,0.7)
}

.account-recovery-link
{
	margin-bottom:16px
}

.login #nosms
{
	font-size:13px;
	padding:0 24px;
	margin:16px 0 0
}

.twostep-login-details strong
{
	white-space:nowrap
}

.hr2-core-ui .button,.hr2-core-ui .button-primary,.hr2-core-ui .button-secondary
{
	display:inline-block;
	text-decoration:none;
	font-size:13px;
	line-height:26px;
	height:28px;
	margin:0;
	padding:0 10px 1px;
	cursor:pointer;
	border-width:1px;
	border-style:solid;
	-webkit-appearance:none;
	-webkit-border-radius:3px;
	border-radius:3px;
	white-space:nowrap;
	-webkit-box-sizing:border-box;
	-moz-box-sizing:border-box;
	box-sizing:border-box
}

.hr2-core-ui button::-moz-focus-inner,.hr2-core-ui input[type=reset]::-moz-focus-inner,.hr2-core-ui input[type=button]::-moz-focus-inner,.hr2-core-ui input[type=submit]::-moz-focus-inner
{
	border-width:0;
	border-style:none;
	padding:0
}

.hr2-core-ui .button-group.button-large .button,.hr2-core-ui .button.button-large
{
	height:30px;
	line-height:28px;
	padding:0 12px 2px
}

.hr2-core-ui .button-group.button-small .button,.hr2-core-ui .button.button-small
{
	height:24px;
	line-height:22px;
	padding:0 8px 1px;
	font-size:11px
}

.hr2-core-ui .button-group.button-hero .button,.hr2-core-ui .button.button-hero
{
	font-size:14px;
	height:46px;
	line-height:44px;
	padding:0 36px
}

.hr2-core-ui .button:active,.hr2-core-ui .button:focus
{
	outline:0
}

.ie8 .hr2-core-ui .button-link:focus
{
	outline:#5b9dd9 solid 1px
}

.hr2-core-ui .button.hidden
{
	display:none
}

.hr2-core-ui input[type=reset],.hr2-core-ui input[type=reset]:active,.hr2-core-ui input[type=reset]:focus,.hr2-core-ui input[type=reset]:hover
{
	background:0 0;
	border:none;
	-webkit-box-shadow:none;
	box-shadow:none;
	padding:0 2px 1px;
	width:auto
}

.hr2-core-ui .button,.hr2-core-ui .button-secondary
{
	color:#555;
	border-color:#ccc;
	background:#f7f7f7;
	-webkit-box-shadow:inset 0 1px 0 #fff,0 1px 0 rgba(0,0,0,.08);
	box-shadow:inset 0 1px 0 #fff,0 1px 0 rgba(0,0,0,.08);
	vertical-align:top
}

.hr2-core-ui p .button
{
	vertical-align:baseline
}

.hr2-core-ui .button-link
{
	border:0;
	background:0 0;
	outline:0;
	cursor:pointer
}

.hr2-core-ui .button-secondary:focus,.hr2-core-ui .button-secondary:hover,.hr2-core-ui .button.focus,.hr2-core-ui .button.hover,.hr2-core-ui .button:focus,.hr2-core-ui .button:hover
{
	background:#fafafa;
	border-color:#999;
	color:#23282d
}

.hr2-core-ui .button-link:focus,.hr2-core-ui .button-secondary:focus,.hr2-core-ui .button.focus,.hr2-core-ui .button:focus
{
	-webkit-box-shadow:0 0 0 1px #5b9dd9,0 0 2px 1px rgba(30,140,190,.8);
	box-shadow:0 0 0 1px #5b9dd9,0 0 2px 1px rgba(30,140,190,.8)
}

.hr2-core-ui .button-secondary:active,.hr2-core-ui .button.active,.hr2-core-ui .button.active:hover,.hr2-core-ui .button:active
{
	background:#eee;
	border-color:#999;
	color:#32373c;
	-webkit-box-shadow:inset 0 2px 5px -3px rgba(0,0,0,.5);
	box-shadow:inset 0 2px 5px -3px rgba(0,0,0,.5)
}

.hr2-core-ui .button.active:focus
{
	-webkit-box-shadow:inset 0 2px 5px -3px rgba(0,0,0,.5),0 0 0 1px #5b9dd9,0 0 2px 1px rgba(30,140,190,.8);
	box-shadow:inset 0 2px 5px -3px rgba(0,0,0,.5),0 0 0 1px #5b9dd9,0 0 2px 1px rgba(30,140,190,.8)
}

.hr2-core-ui .button-disabled,.hr2-core-ui .button-secondary.disabled,.hr2-core-ui .button-secondary:disabled,.hr2-core-ui .button-secondary[disabled],.hr2-core-ui .button.disabled,.hr2-core-ui .button:disabled,.hr2-core-ui .button[disabled]
{
	color:#a0a5aa!important;
	border-color:#ddd!important;
	background:#f7f7f7!important;
	-webkit-box-shadow:none!important;
	box-shadow:none!important;
	text-shadow:0 1px 0 #fff!important;
	cursor:default
}

.hr2-core-ui .button-primary
{
	background:#00a0d2;
	border-color:#0073aa;
	-webkit-box-shadow:inset 0 1px 0 rgba(120,200,230,.5),0 1px 0 rgba(0,0,0,.15);
	box-shadow:inset 0 1px 0 rgba(120,200,230,.5),0 1px 0 rgba(0,0,0,.15);
	color:#fff;
	text-decoration:none
}

.hr2-core-ui .button-primary.focus,.hr2-core-ui .button-primary.hover,.hr2-core-ui .button-primary:focus,.hr2-core-ui .button-primary:hover
{
	background:#0091cd;
	border-color:#0073aa;
	-webkit-box-shadow:inset 0 1px 0 rgba(120,200,230,.6);
	box-shadow:inset 0 1px 0 rgba(120,200,230,.6);
	color:#fff
}

.hr2-core-ui .button-primary.focus,.hr2-core-ui .button-primary:focus
{
	border-color:#0e3950;
	-webkit-box-shadow:inset 0 1px 0 rgba(120,200,230,.6),0 0 0 1px #5b9dd9,0 0 2px 1px rgba(30,140,190,.8);
	box-shadow:inset 0 1px 0 rgba(120,200,230,.6),0 0 0 1px #5b9dd9,0 0 2px 1px rgba(30,140,190,.8)
}

.hr2-core-ui .button-primary.active,.hr2-core-ui .button-primary.active:focus,.hr2-core-ui .button-primary.active:hover,.hr2-core-ui .button-primary:active
{
	background:#0073aa;
	border-color:#005082;
	color:rgba(255,255,255,.95);
	-webkit-box-shadow:inset 0 1px 0 rgba(0,0,0,.1);
	box-shadow:inset 0 1px 0 rgba(0,0,0,.1);
	vertical-align:top
}

.hr2-core-ui .button-primary-disabled,.hr2-core-ui .button-primary.disabled,.hr2-core-ui .button-primary:disabled,.hr2-core-ui .button-primary[disabled]
{
	color:#94cde7!important;
	background:#298cba!important;
	border-color:#1b607f!important;
	-webkit-box-shadow:none!important;
	box-shadow:none!important;
	text-shadow:0 -1px 0 rgba(0,0,0,.1)!important;
	cursor:default
}

.hr2-core-ui .button-group
{
	position:relative;
	display:inline-block;
	white-space:nowrap;
	font-size:0;
	vertical-align:middle
}

.hr2-core-ui .button-group>.button
{
	display:inline-block;
	-webkit-border-radius:0;
	border-radius:0;
	margin-right:-1px;
	z-index:10
}

.hr2-core-ui .button-group>.button-primary
{
	z-index:100
}

.hr2-core-ui .button-group>.button:hover
{
	z-index:20
}

.hr2-core-ui .button-group>.button:first-child
{
	-webkit-border-radius:3px 0 0 3px;
	border-radius:3px 0 0 3px
}

.hr2-core-ui .button-group>.button:last-child
{
	-webkit-border-radius:0 3px 3px 0;
	border-radius:0 3px 3px 0
}

.hr2-core-ui .button-group>.button:focus
{
	position:relative;
	z-index:1
}

@media screen and (max-width:782px) {
	.hr2-core-ui .button,.hr2-core-ui .button.button-large,.hr2-core-ui .button.button-small,a.preview,input#publish,input#save-post
	{
		padding:6px 14px;
		line-height:normal;
		font-size:14px;
		vertical-align:middle;
		height:auto;
		margin-bottom:4px
	}
	
	#media-upload.hr2-core-ui .button
	{
		padding:0 10px 1px;
		height:24px;
		line-height:22px;
		font-size:13px
	}
	
	.media-frame.mode-grid .bulk-select .button
	{
		margin-bottom:0
	}
	
	.hr2-core-ui .save-post-status.button
	{
		position:relative;
		margin:0 14px 0 10px
	}
	
	.press-this a.preview,.press-this input#publish,.press-this input#save-post,.press-this.hr2-core-ui .button,.hr2-core-ui.hr2-customizer .button
	{
		padding:0 10px 1px;
		font-size:13px;
		line-height:26px;
		height:28px;
		margin:0;
		vertical-align:inherit
	}
	
	.interim-login .button.button-large
	{
		height:30px;
		line-height:28px;
		padding:0 12px 2px
	}
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:300;
/* 	src:local('Open Sans Light'),local(OpenSans-Light),url(https://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTa-j2U0lmluP9RWlSytm3ho.woff2) format("woff2"); */
	unicode-range:U+0460-052F,U+20B4,U+2DE0-2DFF,U+A640-A69F
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:300;
/* 	src:local('Open Sans Light'),local(OpenSans-Light),url(https://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTZX5f-9o1vgP2EXwfjgl7AY.woff2) format("woff2"); */
	unicode-range:U+0400-045F,U+0490-0491,U+04B0-04B1,U+2116
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:300;
/* 	src:local('Open Sans Light'),local(OpenSans-Light),url(https://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTRWV49_lSm1NYrwo-zkhivY.woff2) format("woff2"); */
	unicode-range:U+1F00-1FFF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:300;
/* 	src:local('Open Sans Light'),local(OpenSans-Light),url(https://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTaaRobkAwv3vxw3jMhVENGA.woff2) format("woff2"); */
	unicode-range:U+0370-03FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:300;
/* 	src:local('Open Sans Light'),local(OpenSans-Light),url(https://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTf8zf_FOSsgRmwsS7Aa9k2w.woff2) format("woff2"); */
	unicode-range:U+0102-0103,U+1EA0-1EF1,U+20AB
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:300;
/* 	src:local('Open Sans Light'),local(OpenSans-Light),url(https://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTT0LW-43aMEzIO6XUTLjad8.woff2) format("woff2"); */
	unicode-range:U+0100-024F,U+1E00-1EFF,U+20A0-20AB,U+20AD-20CF,U+2C60-2C7F,U+A720-A7FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:300;
/* 	src:local('Open Sans Light'),local(OpenSans-Light),url(https://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTegdm0LZdjqr5-oayXSOefg.woff2) format("woff2"); */
	unicode-range:U+0000-00FF,U+0131,U+0152-0153,U+02C6,U+02DA,U+02DC,U+2000-206F,U+2074,U+20AC,U+2212,U+2215,U+E0FF,U+EFFD,U+F000
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:400;
/* 	src:local('Open Sans'),local(OpenSans),url(https://fonts.gstatic.com/s/opensans/v13/K88pR3goAWT7BTt32Z01mxJtnKITppOI_IvcXXDNrsc.woff2) format("woff2"); */
	unicode-range:U+0460-052F,U+20B4,U+2DE0-2DFF,U+A640-A69F
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:400;
/* 	src:local('Open Sans'),local(OpenSans),url(https://fonts.gstatic.com/s/opensans/v13/RjgO7rYTmqiVp7vzi-Q5URJtnKITppOI_IvcXXDNrsc.woff2) format("woff2"); */
	unicode-range:U+0400-045F,U+0490-0491,U+04B0-04B1,U+2116
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:400;
/* 	src:local('Open Sans'),local(OpenSans),url(https://fonts.gstatic.com/s/opensans/v13/LWCjsQkB6EMdfHrEVqA1KRJtnKITppOI_IvcXXDNrsc.woff2) format("woff2"); */
	unicode-range:U+1F00-1FFF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:400;
/* 	src:local('Open Sans'),local(OpenSans),url(https://fonts.gstatic.com/s/opensans/v13/xozscpT2726on7jbcb_pAhJtnKITppOI_IvcXXDNrsc.woff2) format("woff2"); */
	unicode-range:U+0370-03FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:400;
/* 	src:local('Open Sans'),local(OpenSans),url(https://fonts.gstatic.com/s/opensans/v13/59ZRklaO5bWGqF5A9baEERJtnKITppOI_IvcXXDNrsc.woff2) format("woff2"); */
	unicode-range:U+0102-0103,U+1EA0-1EF1,U+20AB
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:400;
/* 	src:local('Open Sans'),local(OpenSans),url(https://fonts.gstatic.com/s/opensans/v13/u-WUoqrET9fUeobQW7jkRRJtnKITppOI_IvcXXDNrsc.woff2) format("woff2"); */
	unicode-range:U+0100-024F,U+1E00-1EFF,U+20A0-20AB,U+20AD-20CF,U+2C60-2C7F,U+A720-A7FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:400;
/* 	src:local('Open Sans'),local(OpenSans),url(https://fonts.gstatic.com/s/opensans/v13/cJZKeOuBrn4kERxqtaUH3VtXRa8TVwTICgirnJhmVJw.woff2) format("woff2"); */
	unicode-range:U+0000-00FF,U+0131,U+0152-0153,U+02C6,U+02DA,U+02DC,U+2000-206F,U+2074,U+20AC,U+2212,U+2215,U+E0FF,U+EFFD,U+F000
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:600;
/* 	src:local('Open Sans Semibold'),local(OpenSans-Semibold),url(https://fonts.gstatic.com/s/opensans/v13/MTP_ySUJH_bn48VBG8sNSq-j2U0lmluP9RWlSytm3ho.woff2) format("woff2"); */
	unicode-range:U+0460-052F,U+20B4,U+2DE0-2DFF,U+A640-A69F
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:600;
/* 	src:local('Open Sans Semibold'),local(OpenSans-Semibold),url(https://fonts.gstatic.com/s/opensans/v13/MTP_ySUJH_bn48VBG8sNSpX5f-9o1vgP2EXwfjgl7AY.woff2) format("woff2"); */
	unicode-range:U+0400-045F,U+0490-0491,U+04B0-04B1,U+2116
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:600;
/* 	src:local('Open Sans Semibold'),local(OpenSans-Semibold),url(https://fonts.gstatic.com/s/opensans/v13/MTP_ySUJH_bn48VBG8sNShWV49_lSm1NYrwo-zkhivY.woff2) format("woff2"); */
	unicode-range:U+1F00-1FFF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:600;
/* 	src:local('Open Sans Semibold'),local(OpenSans-Semibold),url(https://fonts.gstatic.com/s/opensans/v13/MTP_ySUJH_bn48VBG8sNSqaRobkAwv3vxw3jMhVENGA.woff2) format("woff2"); */
	unicode-range:U+0370-03FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:600;
/* 	src:local('Open Sans Semibold'),local(OpenSans-Semibold),url(https://fonts.gstatic.com/s/opensans/v13/MTP_ySUJH_bn48VBG8sNSv8zf_FOSsgRmwsS7Aa9k2w.woff2) format("woff2"); */
	unicode-range:U+0102-0103,U+1EA0-1EF1,U+20AB
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:600;
/* 	src:local('Open Sans Semibold'),local(OpenSans-Semibold),url(https://fonts.gstatic.com/s/opensans/v13/MTP_ySUJH_bn48VBG8sNSj0LW-43aMEzIO6XUTLjad8.woff2) format("woff2"); */
	unicode-range:U+0100-024F,U+1E00-1EFF,U+20A0-20AB,U+20AD-20CF,U+2C60-2C7F,U+A720-A7FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:normal;
	font-weight:600;
/* 	src:local('Open Sans Semibold'),local(OpenSans-Semibold),url(https://fonts.gstatic.com/s/opensans/v13/MTP_ySUJH_bn48VBG8sNSugdm0LZdjqr5-oayXSOefg.woff2) format("woff2"); */
	unicode-range:U+0000-00FF,U+0131,U+0152-0153,U+02C6,U+02DA,U+02DC,U+2000-206F,U+2074,U+20AC,U+2212,U+2215,U+E0FF,U+EFFD,U+F000
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:300;
/* 	src:local('Open Sans Light Italic'),local(OpenSansLight-Italic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxhgVThLs8Y7ETJzDCYFCSLE.woff2) format("woff2"); */
	unicode-range:U+0460-052F,U+20B4,U+2DE0-2DFF,U+A640-A69F
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:300;
/* 	src:local('Open Sans Light Italic'),local(OpenSansLight-Italic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxpiMaisvaUVUsYyVzOmndek.woff2) format("woff2"); */
	unicode-range:U+0400-045F,U+0490-0491,U+04B0-04B1,U+2116
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:300;
/* 	src:local('Open Sans Light Italic'),local(OpenSansLight-Italic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxrBAWGjcah5Ky0jbCgIwDB8.woff2) format("woff2"); */
	unicode-range:U+1F00-1FFF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:300;
/* 	src:local('Open Sans Light Italic'),local(OpenSansLight-Italic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxv14vlcfyPYlAcQy2UfDRm4.woff2) format("woff2"); */
	unicode-range:U+0370-03FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:300;
/* 	src:local('Open Sans Light Italic'),local(OpenSansLight-Italic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxqfJul7RR1X4poJgi27uS4w.woff2) format("woff2"); */
	unicode-range:U+0102-0103,U+1EA0-1EF1,U+20AB
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:300;
/* 	src:local('Open Sans Light Italic'),local(OpenSansLight-Italic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxqvyPXdneeGd26m9EmFSSWg.woff2) format("woff2"); */
	unicode-range:U+0100-024F,U+1E00-1EFF,U+20A0-20AB,U+20AD-20CF,U+2C60-2C7F,U+A720-A7FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:300;
/* 	src:local('Open Sans Light Italic'),local(OpenSansLight-Italic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxko2lTMeWA_kmIyWrkNChr2c.woff2) format("woff2"); */
	unicode-range:U+0000-00FF,U+0131,U+0152-0153,U+02C6,U+02DA,U+02DC,U+2000-206F,U+2074,U+20AC,U+2212,U+2215,U+E0FF,U+EFFD,U+F000
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:400;
/* 	src:local('Open Sans Italic'),local(OpenSans-Italic),url(https://fonts.gstatic.com/s/opensans/v13/xjAJXh38I15wypJXxuGMBjTOQ_MqJVwkKsUn0wKzc2I.woff2) format("woff2"); */
	unicode-range:U+0460-052F,U+20B4,U+2DE0-2DFF,U+A640-A69F
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:400;
/* 	src:local('Open Sans Italic'),local(OpenSans-Italic),url(https://fonts.gstatic.com/s/opensans/v13/xjAJXh38I15wypJXxuGMBjUj_cnvWIuuBMVgbX098Mw.woff2) format("woff2"); */
	unicode-range:U+0400-045F,U+0490-0491,U+04B0-04B1,U+2116
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:400;
/* 	src:local('Open Sans Italic'),local(OpenSans-Italic),url(https://fonts.gstatic.com/s/opensans/v13/xjAJXh38I15wypJXxuGMBkbcKLIaa1LC45dFaAfauRA.woff2) format("woff2"); */
	unicode-range:U+1F00-1FFF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:400;
/* 	src:local('Open Sans Italic'),local(OpenSans-Italic),url(https://fonts.gstatic.com/s/opensans/v13/xjAJXh38I15wypJXxuGMBmo_sUJ8uO4YLWRInS22T3Y.woff2) format("woff2"); */
	unicode-range:U+0370-03FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:400;
/* 	src:local('Open Sans Italic'),local(OpenSans-Italic),url(https://fonts.gstatic.com/s/opensans/v13/xjAJXh38I15wypJXxuGMBr6up8jxqWt8HVA3mDhkV_0.woff2) format("woff2"); */
	unicode-range:U+0102-0103,U+1EA0-1EF1,U+20AB
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:400;
/* 	src:local('Open Sans Italic'),local(OpenSans-Italic),url(https://fonts.gstatic.com/s/opensans/v13/xjAJXh38I15wypJXxuGMBiYE0-AqJ3nfInTTiDXDjU4.woff2) format("woff2"); */
	unicode-range:U+0100-024F,U+1E00-1EFF,U+20A0-20AB,U+20AD-20CF,U+2C60-2C7F,U+A720-A7FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:400;
/* 	src:local('Open Sans Italic'),local(OpenSans-Italic),url(https://fonts.gstatic.com/s/opensans/v13/xjAJXh38I15wypJXxuGMBo4P5ICox8Kq3LLUNMylGO4.woff2) format("woff2"); */
	unicode-range:U+0000-00FF,U+0131,U+0152-0153,U+02C6,U+02DA,U+02DC,U+2000-206F,U+2074,U+20AC,U+2212,U+2215,U+E0FF,U+EFFD,U+F000
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:600;
/* 	src:local('Open Sans Semibold Italic'),local(OpenSans-SemiboldItalic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxmgpAmOCqD37_tyH_8Ri5MM.woff2) format("woff2"); */
	unicode-range:U+0460-052F,U+20B4,U+2DE0-2DFF,U+A640-A69F
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:600;
/* 	src:local('Open Sans Semibold Italic'),local(OpenSans-SemiboldItalic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxsPNMTLbnS9uQzHQlYieHUU.woff2) format("woff2"); */
	unicode-range:U+0400-045F,U+0490-0491,U+04B0-04B1,U+2116
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:600;
/* 	src:local('Open Sans Semibold Italic'),local(OpenSans-SemiboldItalic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxgyhumQnPMBCoGYhRaNxyyY.woff2) format("woff2"); */
	unicode-range:U+1F00-1FFF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:600;
/* 	src:local('Open Sans Semibold Italic'),local(OpenSans-SemiboldItalic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxhUVAXEdVvYDDqrz3aeR0Yc.woff2) format("woff2"); */
	unicode-range:U+0370-03FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:600;
/* 	src:local('Open Sans Semibold Italic'),local(OpenSans-SemiboldItalic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxlf4y_3s5bcYyyLIFUSWYUU.woff2) format("woff2"); */
	unicode-range:U+0102-0103,U+1EA0-1EF1,U+20AB
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:600;
/* 	src:local('Open Sans Semibold Italic'),local(OpenSans-SemiboldItalic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxnywqdtBbUHn3VPgzuFrCy8.woff2) format("woff2"); */
	unicode-range:U+0100-024F,U+1E00-1EFF,U+20A0-20AB,U+20AD-20CF,U+2C60-2C7F,U+A720-A7FF
}

@font-face
{
	font-family:'Open Sans';
	font-style:italic;
	font-weight:600;
/* 	src:local('Open Sans Semibold Italic'),local(OpenSans-SemiboldItalic),url(https://fonts.gstatic.com/s/opensans/v13/PRmiXeptR36kaC0GEAetxl2umOyRU7PgRiv8DXcgJjk.woff2) format("woff2"); */
	unicode-range:U+0000-00FF,U+0131,U+0152-0153,U+02C6,U+02DA,U+02DC,U+2000-206F,U+2074,U+20AC,U+2212,U+2215,U+E0FF,U+EFFD,U+F000
}

https://s2.hr2.com/_static/??/hr2-includes/css/dashicons.min.css,/hr2-admin/css/login.min.css?m=1442435447j
@font-face
{
	font-family:dashicons;
	src:url(/hr2-includes/css/../fonts/dashicons.eot)
}

@font-face
{
	font-family:dashicons;
/* 	src:url(data:application/font-woff;charset=utf-8;base64,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) format("woff"),url(/hr2-includes/css/../fonts/dashicons.ttf) format("truetype"),url(/hr2-includes/css/../fonts/dashicons.svg#dashicons) format("svg"); */
	font-weight:400;
	font-style:normal
}

.dashicons,.dashicons-before:before
{
	display:inline-block;
	width:20px;
	height:20px;
	font-size:20px;
	line-height:1;
	font-family:dashicons;
	text-decoration:inherit;
	font-weight:400;
	font-style:normal;
	vertical-align:top;
	text-align:center;
	-webkit-transition:color .1s ease-in 0;
	transition:color .1s ease-in 0;
	-webkit-font-smoothing:antialiased;
	-moz-osx-font-smoothing:grayscale
}

.dashicons-menu:before
{
	content:"\f333"
}

.dashicons-admin-site:before
{
	content:"\f319"
}

.dashicons-dashboard:before
{
	content:"\f226"
}

.dashicons-admin-media:before
{
	content:"\f104"
}

.dashicons-admin-page:before
{
	content:"\f105"
}

.dashicons-admin-comments:before
{
	content:"\f101"
}

.dashicons-admin-appearance:before
{
	content:"\f100"
}

.dashicons-admin-plugins:before
{
	content:"\f106"
}

.dashicons-admin-users:before
{
	content:"\f110"
}

.dashicons-admin-tools:before
{
	content:"\f107"
}

.dashicons-admin-settings:before
{
	content:"\f108"
}

.dashicons-admin-network:before
{
	content:"\f112"
}

.dashicons-admin-generic:before
{
	content:"\f111"
}

.dashicons-admin-home:before
{
	content:"\f102"
}

.dashicons-admin-collapse:before
{
	content:"\f148"
}

.dashicons-filter:before
{
	content:"\f536"
}

.dashicons-admin-customizer:before
{
	content:"\f540"
}

.dashicons-admin-multisite:before
{
	content:"\f541"
}

.dashicons-admin-links:before,.dashicons-format-links:before
{
	content:"\f103"
}

.dashicons-admin-post:before,.dashicons-format-standard:before
{
	content:"\f109"
}

.dashicons-format-image:before
{
	content:"\f128"
}

.dashicons-format-gallery:before
{
	content:"\f161"
}

.dashicons-format-audio:before
{
	content:"\f127"
}

.dashicons-format-video:before
{
	content:"\f126"
}

.dashicons-format-chat:before
{
	content:"\f125"
}

.dashicons-format-status:before
{
	content:"\f130"
}

.dashicons-format-aside:before
{
	content:"\f123"
}

.dashicons-format-quote:before
{
	content:"\f122"
}

.dashicons-welcome-edit-page:before,.dashicons-welcome-write-blog:before
{
	content:"\f119"
}

.dashicons-welcome-add-page:before
{
	content:"\f133"
}

.dashicons-welcome-view-site:before
{
	content:"\f115"
}

.dashicons-welcome-widgets-menus:before
{
	content:"\f116"
}

.dashicons-welcome-comments:before
{
	content:"\f117"
}

.dashicons-welcome-learn-more:before
{
	content:"\f118"
}

.dashicons-image-crop:before
{
	content:"\f165"
}

.dashicons-image-rotate:before
{
	content:"\f531"
}

.dashicons-image-rotate-left:before
{
	content:"\f166"
}

.dashicons-image-rotate-right:before
{
	content:"\f167"
}

.dashicons-image-flip-vertical:before
{
	content:"\f168"
}

.dashicons-image-flip-horizontal:before
{
	content:"\f169"
}

.dashicons-image-filter:before
{
	content:"\f533"
}

.dashicons-undo:before
{
	content:"\f171"
}

.dashicons-redo:before
{
	content:"\f172"
}

.dashicons-editor-bold:before
{
	content:"\f200"
}

.dashicons-editor-italic:before
{
	content:"\f201"
}

.dashicons-editor-ul:before
{
	content:"\f203"
}

.dashicons-editor-ol:before
{
	content:"\f204"
}

.dashicons-editor-quote:before
{
	content:"\f205"
}

.dashicons-editor-alignleft:before
{
	content:"\f206"
}

.dashicons-editor-aligncenter:before
{
	content:"\f207"
}

.dashicons-editor-alignright:before
{
	content:"\f208"
}

.dashicons-editor-insertmore:before
{
	content:"\f209"
}

.dashicons-editor-spellcheck:before
{
	content:"\f210"
}

.dashicons-editor-distractionfree:before,.dashicons-editor-expand:before
{
	content:"\f211"
}

.dashicons-editor-contract:before
{
	content:"\f506"
}

.dashicons-editor-kitchensink:before
{
	content:"\f212"
}

.dashicons-editor-underline:before
{
	content:"\f213"
}

.dashicons-editor-justify:before
{
	content:"\f214"
}

.dashicons-editor-textcolor:before
{
	content:"\f215"
}

.dashicons-editor-paste-word:before
{
	content:"\f216"
}

.dashicons-editor-paste-text:before
{
	content:"\f217"
}

.dashicons-editor-removeformatting:before
{
	content:"\f218"
}

.dashicons-editor-video:before
{
	content:"\f219"
}

.dashicons-editor-customchar:before
{
	content:"\f220"
}

.dashicons-editor-outdent:before
{
	content:"\f221"
}

.dashicons-editor-indent:before
{
	content:"\f222"
}

.dashicons-editor-help:before
{
	content:"\f223"
}

.dashicons-editor-strikethrough:before
{
	content:"\f224"
}

.dashicons-editor-unlink:before
{
	content:"\f225"
}

.dashicons-editor-rtl:before
{
	content:"\f320"
}

.dashicons-editor-break:before
{
	content:"\f474"
}

.dashicons-editor-code:before
{
	content:"\f475"
}

.dashicons-editor-paragraph:before
{
	content:"\f476"
}

.dashicons-editor-table:before
{
	content:"\f535"
}

.dashicons-align-left:before
{
	content:"\f135"
}

.dashicons-align-right:before
{
	content:"\f136"
}

.dashicons-align-center:before
{
	content:"\f134"
}

.dashicons-align-none:before
{
	content:"\f138"
}

.dashicons-lock:before
{
	content:"\f160"
}

.dashicons-unlock:before
{
	content:"\f528"
}

.dashicons-calendar:before
{
	content:"\f145"
}

.dashicons-calendar-alt:before
{
	content:"\f508"
}

.dashicons-visibility:before
{
	content:"\f177"
}

.dashicons-hidden:before
{
	content:"\f530"
}

.dashicons-post-status:before
{
	content:"\f173"
}

.dashicons-edit:before
{
	content:"\f464"
}

.dashicons-post-trash:before,.dashicons-trash:before
{
	content:"\f182"
}

.dashicons-sticky:before
{
	content:"\f537"
}

.dashicons-external:before
{
	content:"\f504"
}

.dashicons-arrow-up:before
{
	content:"\f142"
}

.dashicons-arrow-down:before
{
	content:"\f140"
}

.dashicons-arrow-left:before
{
	content:"\f141"
}

.dashicons-arrow-right:before
{
	content:"\f139"
}

.dashicons-arrow-up-alt:before
{
	content:"\f342"
}

.dashicons-arrow-down-alt:before
{
	content:"\f346"
}

.dashicons-arrow-left-alt:before
{
	content:"\f340"
}

.dashicons-arrow-right-alt:before
{
	content:"\f344"
}

.dashicons-arrow-up-alt2:before
{
	content:"\f343"
}

.dashicons-arrow-down-alt2:before
{
	content:"\f347"
}

.dashicons-arrow-left-alt2:before
{
	content:"\f341"
}

.dashicons-arrow-right-alt2:before
{
	content:"\f345"
}

.dashicons-leftright:before
{
	content:"\f229"
}

.dashicons-sort:before
{
	content:"\f156"
}

.dashicons-randomize:before
{
	content:"\f503"
}

.dashicons-list-view:before
{
	content:"\f163"
}

.dashicons-excerpt-view:before,.dashicons-exerpt-view:before
{
	content:"\f164"
}

.dashicons-grid-view:before
{
	content:"\f509"
}

.dashicons-hammer:before
{
	content:"\f308"
}

.dashicons-art:before
{
	content:"\f309"
}

.dashicons-migrate:before
{
	content:"\f310"
}

.dashicons-performance:before
{
	content:"\f311"
}

.dashicons-universal-access:before
{
	content:"\f483"
}

.dashicons-universal-access-alt:before
{
	content:"\f507"
}

.dashicons-tickets:before
{
	content:"\f486"
}

.dashicons-nametag:before
{
	content:"\f484"
}

.dashicons-clipboard:before
{
	content:"\f481"
}

.dashicons-heart:before
{
	content:"\f487"
}

.dashicons-megaphone:before
{
	content:"\f488"
}

.dashicons-schedule:before
{
	content:"\f489"
}

.dashicons-wordpress:before
{
	content:"\f120"
}

.dashicons-wordpress-alt:before
{
	content:"\f324"
}

.dashicons-pressthis:before
{
	content:"\f157"
}

.dashicons-update:before
{
	content:"\f463"
}

.dashicons-screenoptions:before
{
	content:"\f180"
}

.dashicons-cart:before
{
	content:"\f174"
}

.dashicons-feedback:before
{
	content:"\f175"
}

.dashicons-cloud:before
{
	content:"\f176"
}

.dashicons-translation:before
{
	content:"\f326"
}

.dashicons-tag:before
{
	content:"\f323"
}

.dashicons-category:before
{
	content:"\f318"
}

.dashicons-archive:before
{
	content:"\f480"
}

.dashicons-tagcloud:before
{
	content:"\f479"
}

.dashicons-text:before
{
	content:"\f478"
}

.dashicons-media-archive:before
{
	content:"\f501"
}

.dashicons-media-audio:before
{
	content:"\f500"
}

.dashicons-media-code:before
{
	content:"\f499"
}

.dashicons-media-default:before
{
	content:"\f498"
}

.dashicons-media-document:before
{
	content:"\f497"
}

.dashicons-media-interactive:before
{
	content:"\f496"
}

.dashicons-media-spreadsheet:before
{
	content:"\f495"
}

.dashicons-media-text:before
{
	content:"\f491"
}

.dashicons-media-video:before
{
	content:"\f490"
}

.dashicons-playlist-audio:before
{
	content:"\f492"
}

.dashicons-playlist-video:before
{
	content:"\f493"
}

.dashicons-controls-play:before
{
	content:"\f522"
}

.dashicons-controls-pause:before
{
	content:"\f523"
}

.dashicons-controls-forward:before
{
	content:"\f519"
}

.dashicons-controls-skipforward:before
{
	content:"\f517"
}

.dashicons-controls-back:before
{
	content:"\f518"
}

.dashicons-controls-skipback:before
{
	content:"\f516"
}

.dashicons-controls-repeat:before
{
	content:"\f515"
}

.dashicons-controls-volumeon:before
{
	content:"\f521"
}

.dashicons-controls-volumeoff:before
{
	content:"\f520"
}

.dashicons-yes:before
{
	content:"\f147"
}

.dashicons-no:before
{
	content:"\f158"
}

.dashicons-no-alt:before
{
	content:"\f335"
}

.dashicons-plus:before
{
	content:"\f132"
}

.dashicons-plus-alt:before
{
	content:"\f502"
}

.dashicons-plus-alt2:before
{
	content:"\f543"
}

.dashicons-minus:before
{
	content:"\f460"
}

.dashicons-dismiss:before
{
	content:"\f153"
}

.dashicons-marker:before
{
	content:"\f159"
}

.dashicons-star-filled:before
{
	content:"\f155"
}

.dashicons-star-half:before
{
	content:"\f459"
}

.dashicons-star-empty:before
{
	content:"\f154"
}

.dashicons-flag:before
{
	content:"\f227"
}

.dashicons-info:before
{
	content:"\f348"
}

.dashicons-warning:before
{
	content:"\f534"
}

.dashicons-share1:before,.dashicons-share:before
{
	content:"\f237"
}

.dashicons-share-alt:before
{
	content:"\f240"
}

.dashicons-share-alt2:before
{
	content:"\f242"
}

.dashicons-twitter:before
{
	content:"\f301"
}

.dashicons-rss:before
{
	content:"\f303"
}

.dashicons-email:before
{
	content:"\f465"
}

.dashicons-email-alt:before
{
	content:"\f466"
}

.dashicons-facebook:before
{
	content:"\f304"
}

.dashicons-facebook-alt:before
{
	content:"\f305"
}

.dashicons-networking:before
{
	content:"\f325"
}

.dashicons-googleplus:before
{
	content:"\f462"
}

.dashicons-location:before
{
	content:"\f230"
}

.dashicons-location-alt:before
{
	content:"\f231"
}

.dashicons-camera:before
{
	content:"\f306"
}

.dashicons-images-alt:before
{
	content:"\f232"
}

.dashicons-images-alt2:before
{
	content:"\f233"
}

.dashicons-video-alt:before
{
	content:"\f234"
}

.dashicons-video-alt2:before
{
	content:"\f235"
}

.dashicons-video-alt3:before
{
	content:"\f236"
}

.dashicons-vault:before
{
	content:"\f178"
}

.dashicons-shield:before
{
	content:"\f332"
}

.dashicons-shield-alt:before
{
	content:"\f334"
}

.dashicons-sos:before
{
	content:"\f468"
}

.dashicons-search:before
{
	content:"\f179"
}

.dashicons-slides:before
{
	content:"\f181"
}

.dashicons-analytics:before
{
	content:"\f183"
}

.dashicons-chart-pie:before
{
	content:"\f184"
}

.dashicons-chart-bar:before
{
	content:"\f185"
}

.dashicons-chart-line:before
{
	content:"\f238"
}

.dashicons-chart-area:before
{
	content:"\f239"
}

.dashicons-groups:before
{
	content:"\f307"
}

.dashicons-businessman:before
{
	content:"\f338"
}

.dashicons-id:before
{
	content:"\f336"
}

.dashicons-id-alt:before
{
	content:"\f337"
}

.dashicons-products:before
{
	content:"\f312"
}

.dashicons-awards:before
{
	content:"\f313"
}

.dashicons-forms:before
{
	content:"\f314"
}

.dashicons-testimonial:before
{
	content:"\f473"
}

.dashicons-portfolio:before
{
	content:"\f322"
}

.dashicons-book:before
{
	content:"\f330"
}

.dashicons-book-alt:before
{
	content:"\f331"
}

.dashicons-download:before
{
	content:"\f316"
}

.dashicons-upload:before
{
	content:"\f317"
}

.dashicons-backup:before
{
	content:"\f321"
}

.dashicons-clock:before
{
	content:"\f469"
}

.dashicons-lightbulb:before
{
	content:"\f339"
}

.dashicons-microphone:before
{
	content:"\f482"
}

.dashicons-desktop:before
{
	content:"\f472"
}

.dashicons-tablet:before
{
	content:"\f471"
}

.dashicons-smartphone:before
{
	content:"\f470"
}

.dashicons-phone:before
{
	content:"\f525"
}

.dashicons-smiley:before
{
	content:"\f328"
}

.dashicons-index-card:before
{
	content:"\f510"
}

.dashicons-carrot:before
{
	content:"\f511"
}

.dashicons-building:before
{
	content:"\f512"
}

.dashicons-store:before
{
	content:"\f513"
}

.dashicons-album:before
{
	content:"\f514"
}

.dashicons-palmtree:before
{
	content:"\f527"
}

.dashicons-tickets-alt:before
{
	content:"\f524"
}

.dashicons-money:before
{
	content:"\f526"
}

.dashicons-thumbs-up:before
{
	content:"\f529"
}

.dashicons-thumbs-down:before
{
	content:"\f542"
}

.dashicons-layout:before
{
	content:"\f538"
}

#pass-strength-result,input,textarea
{
	-webkit-box-sizing:border-box;
	-moz-box-sizing:border-box
}

#pass1-text,.pw-weak,.show-password #pass1
{
	display:none
}

.meta-box-sortables select,p.submit
{
	max-width:100%
}

#pressthis-code-wrap,textarea
{
	overflow:auto
}

input,textarea
{
	box-sizing:border-box
}

input[type=text],input[type=search],input[type=radio],input[type=tel],input[type=time],input[type=url],input[type=week],input[type=password],input[type=checkbox],input[type=color],input[type=date],input[type=datetime],input[type=datetime-local],input[type=email],input[type=month],input[type=number],select,textarea
{
	border:1px solid #ddd;
	-webkit-box-shadow:inset 0 1px 2px rgba(0,0,0,.07);
	box-shadow:inset 0 1px 2px rgba(0,0,0,.07);
	background-color:#fff;
	color:#32373c;
	outline:0;
	-webkit-transition:.05s border-color ease-in-out;
	transition:.05s border-color ease-in-out
}

input[type=text]:focus,input[type=search]:focus,input[type=radio]:focus,input[type=tel]:focus,input[type=time]:focus,input[type=url]:focus,input[type=week]:focus,input[type=password]:focus,input[type=checkbox]:focus,input[type=color]:focus,input[type=date]:focus,input[type=datetime]:focus,input[type=datetime-local]:focus,input[type=email]:focus,input[type=month]:focus,input[type=number]:focus,select:focus,textarea:focus
{
	border-color:#5b9dd9;
	-webkit-box-shadow:0 0 2px rgba(30,140,190,.8);
	box-shadow:0 0 2px rgba(30,140,190,.8)
}

input[type=url],input[type=email]
{
	direction:ltr
}

input[type=radio],input[type=checkbox]
{
	border:1px solid #b4b9be;
	background:#fff;
	color:#555;
	clear:none;
	cursor:pointer;
	display:inline-block;
	line-height:0;
	height:16px;
	margin:-4px 4px 0 0;
	outline:0;
	padding:0!important;
	text-align:center;
	vertical-align:middle;
	width:16px;
	min-width:16px;
	-webkit-appearance:none;
	-webkit-box-shadow:inset 0 1px 2px rgba(0,0,0,.1);
	box-shadow:inset 0 1px 2px rgba(0,0,0,.1);
	-webkit-transition:.05s border-color ease-in-out;
	transition:.05s border-color ease-in-out
}

input[type=radio]:checked+label:before
{
	color:#82878c
}

.hr2-core-ui input[type=reset]:active,.hr2-core-ui input[type=reset]:hover
{
	color:#00a0d2
}

.hr2-admin p input[type=radio],.hr2-admin p input[type=checkbox],td>input[type=checkbox]
{
	margin-top:0
}

.hr2-admin p label input[type=checkbox]
{
	margin-top:-4px
}

.hr2-admin p label input[type=radio]
{
	margin-top:-2px
}

input[type=radio]
{
	-webkit-border-radius:50%;
	border-radius:50%;
	margin-right:4px;
	line-height:10px
}

input[type=radio]:checked:before,input[type=checkbox]:checked:before
{
	float:left;
	display:inline-block;
	vertical-align:middle;
	width:16px;
	font:400 21px/1 dashicons;
	speak:none;
	-webkit-font-smoothing:antialiased;
	-moz-osx-font-smoothing:grayscale
}

input[type=checkbox]:checked:before
{
	content:'\f147';
	margin:-3px 0 0 -4px;
	color:#1e8cbe
}

input[type=radio]:checked:before
{
	content:'\2022';
	text-indent:-9999px;
	-webkit-border-radius:50px;
	border-radius:50px;
	font-size:24px;
	width:6px;
	height:6px;
	margin:4px;
	line-height:16px;
	background-color:#1e8cbe
}

input[type=search]
{
	-webkit-appearance:textfield
}

input[type=search]::-webkit-search-decoration
{
	display:none
}

.ie8 input[type=password]
{
	font-family:sans-serif
}

button,input,select,textarea
{
	font-family:inherit;
	font-size:inherit;
	font-weight:inherit
}

input,select,textarea
{
	font-size:14px;
	-webkit-border-radius:0;
	border-radius:0
}

textarea
{
	padding:2px 6px;
	line-height:1.4
}

.hr2-admin input[type=file]
{
	padding:3px 0
}

label
{
	cursor:pointer
}

input,select
{
	margin:1px;
	padding:3px 5px
}

input.code
{
	padding-top:6px
}

textarea.code
{
	line-height:1.4;
	padding:4px 6px 1px
}

input.readonly,input[readonly],textarea.readonly,textarea[readonly]
{
	background-color:#eee
}

.hr2-core-ui :-moz-placeholder,:-moz-placeholder
{
	color:#a9a9a9
}

.form-invalid input,.form-invalid input:focus,.form-invalid select,.form-invalid select:focus
{
	border-color:#dc3232!important;
	-webkit-box-shadow:0 0 2px rgba(204,0,0,.8);
	box-shadow:0 0 2px rgba(204,0,0,.8)
}

.form-table .form-required.form-invalid td:after
{
	content:'\f534';
	font:400 20px/1 dashicons;
	color:#dc3232;
	margin-left:-25px;
	vertical-align:middle
}

.form-table .form-required.user-pass1-wrap.form-invalid td:after
{
	content:''
}

.form-table .form-required.user-pass1-wrap.form-invalid .password-input-wrapper:after
{
	content:'\f534';
	font:400 20px/1 dashicons;
	color:#dc3232;
	margin:0 6px 0 -29px;
	vertical-align:middle
}

.form-input-tip
{
	color:#666
}

input.disabled,input:disabled,select.disabled,select:disabled,textarea.disabled,textarea:disabled
{
	background:rgba(255,255,255,.5);
	border-color:rgba(222,222,222,.75);
	-webkit-box-shadow:inset 0 1px 2px rgba(0,0,0,.04);
	box-shadow:inset 0 1px 2px rgba(0,0,0,.04);
	color:rgba(51,51,51,.5)
}

input[type=file].disabled,input[type=file]:disabled,input[type=range].disabled,input[type=range]:disabled
{
	background:0 0;
	-webkit-box-shadow:none;
	box-shadow:none
}

input[type=radio].disabled,input[type=radio].disabled:checked:before,input[type=radio]:disabled,input[type=radio]:disabled:checked:before,input[type=checkbox].disabled,input[type=checkbox].disabled:checked:before,input[type=checkbox]:disabled,input[type=checkbox]:disabled:checked:before
{
	opacity:.7
}

.hr2-admin select
{
	padding:2px;
	line-height:28px;
	height:28px;
	vertical-align:middle
}

.hr2-admin .button-cancel
{
	padding:0 5px;
	line-height:2
}

.hr2-admin select[multiple]
{
	height:auto
}

.submit
{
	padding:1.5em 0;
	margin:5px 0;
	-webkit-border-bottom-left-radius:3px;
	border-bottom-left-radius:3px;
	-webkit-border-bottom-right-radius:3px;
	border-bottom-right-radius:3px;
	border:none
}

form p.submit a.cancel:hover
{
	text-decoration:none
}

p.submit
{
	text-align:left;
	margin-top:20px;
	padding-top:10px
}

.textright p.submit
{
	border:none;
	text-align:right
}

#major-publishing-actions input,#minor-publishing-actions .preview,#minor-publishing-actions input,#pass-strength-result,.login h1
{
	text-align:center
}

table.form-table+input+input+p.submit,table.form-table+input+p.submit,table.form-table+p.submit
{
	border-top:none;
	padding-top:0
}

input.all-options,textarea.all-options
{
	width:250px
}

input.large-text,textarea.large-text
{
	width:99%
}

input.regular-text
{
	width:25em
}

input.small-text
{
	width:50px;
	padding:1px 6px
}

input[type=number].small-text
{
	width:65px
}

#doaction,#doaction2,#post-query-submit
{
	margin:1px 8px 0 0
}

.tablenav #changeit,.tablenav #clear-recent-list,.tablenav #delete_all,.hr2-filter #delete_all
{
	margin-top:1px
}

.tablenav .actions select
{
	float:left;
	margin-right:6px;
	max-width:200px
}

.ie8 .tablenav .actions select
{
	width:155px
}

.ie8 .tablenav .actions select#cat
{
	width:200px
}

#timezone_string option
{
	margin-left:1em
}

#upload-form label
{
	color:#777
}

button.hr2-hide-pw>.dashicons
{
	position:relative;
	top:3px
}

#your-profile label+a,fieldset label,label
{
	vertical-align:middle
}

#misc-publishing-actions label,.options-media-php label[for*="_size_"]
{
	vertical-align:baseline
}

#misc-publishing-actions label[for=post_status]:before
{
	content:'\f173';
	display:inline-block;
	font:400 20px/1 dashicons;
	speak:none;
	left:-1px;
	padding:0 5px 0 0;
	position:relative;
	top:0;
	text-decoration:none!important;
	vertical-align:top;
	-webkit-font-smoothing:antialiased;
	-moz-osx-font-smoothing:grayscale
}

#pass-strength-result
{
	background-color:#eee;
	border:1px solid #ddd;
	color:#23282d;
	margin:-2px 5px 5px 1px;
	padding:3px 5px;
	width:25em;
	box-sizing:border-box;
	opacity:0
}

#pass-strength-result.short
{
	background-color:#f1adad;
	border-color:#e35b5b;
	opacity:1
}

#pass-strength-result.bad
{
	background-color:#fbc5a9;
	border-color:#f78b53;
	opacity:1
}

#pass-strength-result.good
{
	background-color:#ffe399;
	border-color:#ffc733;
	opacity:1
}

#pass-strength-result.strong
{
	background-color:#c1e1b9;
	border-color:#83c373;
	opacity:1
}

#pass1-text.short,#pass1.short
{
	border-color:#e35b5b
}

#pass1-text.bad,#pass1.bad
{
	border-color:#f78b53
}

#pass1-text.good,#pass1.good
{
	border-color:#ffc733
}

#pass1-text.strong,#pass1.strong
{
	border-color:#83c373
}

.indicator-hint
{
	padding-top:8px
}

.show-password #pass1-text
{
	display:inline-block
}

.form-table span.description.important
{
	font-size:12px
}

p.search-box
{
	float:right;
	margin:0
}

.network-admin.themes-php p.search-box
{
	clear:left
}

.search-box input[name="s"],.tablenav .search-plugins input[name="s"],.tagsdiv .newtag
{
	float:left;
	height:28px;
	margin:0 4px 0 0
}

input[type=text].ui-autocomplete-loading
{
	background:url(/hr2-admin/css/../images/loading.gif) right center no-repeat;
	visibility:visible
}

ul#add-to-blog-users
{
	margin:0 0 0 14px
}

.ui-autocomplete-input.open
{
	-webkit-border-bottom-right-radius:0;
	border-bottom-right-radius:0;
	-webkit-border-bottom-left-radius:0;
	border-bottom-left-radius:0
}

.ui-autocomplete
{
	padding:0;
	margin:0;
	list-style:none;
	position:absolute;
	z-index:10000;
	-webkit-border-bottom-right-radius:3px;
	border-bottom-right-radius:3px;
	-webkit-border-bottom-left-radius:3px;
	border-bottom-left-radius:3px;
	border:1px solid #aaa;
	background-color:#efefef
}

.ui-autocomplete li
{
	margin-bottom:0;
	white-space:nowrap;
	text-align:left
}

.ui-autocomplete li a
{
	display:block;
	height:100%;
	padding:4px 10px;
	color:#444
}

.ui-autocomplete li a.ui-state-focus
{
	background-color:#ddd;
	cursor:pointer
}

.form-table
{
	border-collapse:collapse;
	margin-top:.5em;
	width:100%;
	clear:both
}

.form-table,.form-table td,.form-table td p,.form-table th,.form-wrap label
{
	font-size:14px
}

.form-table td
{
	margin-bottom:9px;
	padding:15px 10px;
	line-height:1.3;
	vertical-align:middle
}

.form-table th,.form-wrap label
{
	color:#23282d;
	font-weight:400;
	text-shadow:none;
	vertical-align:baseline
}

.form-table th
{
	vertical-align:top;
	text-align:left;
	padding:20px 10px 20px 0;
	width:200px;
	line-height:1.3;
	font-weight:600
}

.form-table th.th-full
{
	width:auto;
	font-weight:400
}

.form-table td p
{
	margin-top:4px;
	margin-bottom:0
}

.form-table td fieldset label
{
	margin:.25em 0 .5em!important;
	display:inline-block
}

.form-table td fieldset label,.form-table td fieldset li,.form-table td fieldset p
{
	line-height:1.4em
}

.form-table input.tog,.form-table input[type=radio]
{
	margin-top:-4px;
	margin-right:4px;
	float:none
}

.form-table .pre
{
	padding:8px;
	margin:0
}

table.form-table td .updated
{
	font-size:13px
}

table.form-table td .updated p
{
	font-size:13px;
	margin:.3em 0
}

#profile-page .form-table textarea
{
	width:500px;
	margin-bottom:6px
}

#profile-page .form-table #rich_editing
{
	margin-right:5px
}

#your-profile legend
{
	font-size:22px
}

#display_name
{
	width:15em
}

#adduser .form-field input,#createuser .form-field input
{
	width:25em
}

.color-option
{
	display:inline-block;
	width:24%;
	padding:5px 15px 15px;
	-webkit-box-sizing:border-box;
	-moz-box-sizing:border-box;
	box-sizing:border-box;
	margin-bottom:3px;
	cursor:pointer
}

.color-option.selected,.color-option:hover
{
	background:#ddd
}

.color-palette
{
	width:100%;
	border-spacing:0;
	border-collapse:collapse
}

.color-palette td
{
	height:20px;
	padding:0;
	border:none
}

.tool-box .title
{
	margin:8px 0;
	font-size:18px;
	font-weight:400;
	line-height:24px
}

.card
{
	position:relative;
	margin-top:20px;
	padding:.7em 2em 1em;
	min-width:255px;
	max-width:520px;
	border:1px solid #e5e5e5;
	-webkit-box-shadow:0 1px 1px rgba(0,0,0,.04);
	box-shadow:0 1px 1px rgba(0,0,0,.04);
	background:#fff
}

.pressthis h4
{
	margin:2em 0 1em
}

.pressthis textarea
{
	width:100%;
	font-size:1em
}

.login form,.login h1 a
{
	overflow:hidden;
	font-weight:400
}

.pressthis-bookmarklet-wrapper
{
	margin:20px 0 8px;
	vertical-align:top;
	position:relative;
	z-index:1
}

.pressthis-bookmarklet,.pressthis-bookmarklet:active,.pressthis-bookmarklet:focus,.pressthis-bookmarklet:hover
{
	display:inline-block;
	position:relative;
	cursor:move;
	color:#32373c;
	background:#e6e6e6;
	-webkit-border-radius:5px;
	border-radius:5px;
	border:1px solid #b4b4b4;
	font-style:normal;
	line-height:16px;
	font-size:14px;
	text-decoration:none
}

.pressthis-bookmarklet:active
{
	outline:0
}

.pressthis-bookmarklet:after
{
	content:'';
	width:70%;
	height:55%;
	z-index:-1;
	position:absolute;
	right:10px;
	bottom:9px;
	background:0 0;
	-webkit-transform:skew(20deg) rotate(6deg);
	-ms-transform:skew(20deg) rotate(6deg);
	transform:skew(20deg) rotate(6deg);
	-webkit-box-shadow:0 10px 8px rgba(0,0,0,.6);
	box-shadow:0 10px 8px rgba(0,0,0,.6)
}

.pressthis-bookmarklet:hover:after
{
	-webkit-transform:skew(20deg) rotate(9deg);
	-ms-transform:skew(20deg) rotate(9deg);
	transform:skew(20deg) rotate(9deg);
	-webkit-box-shadow:0 10px 8px rgba(0,0,0,.7);
	box-shadow:0 10px 8px rgba(0,0,0,.7)
}

.pressthis-bookmarklet span
{
	display:inline-block;
	margin:0;
	padding:0 12px 8px 9px
}

.pressthis-js-toggle,.pressthis-js-toggle.button.button
{
	margin-left:10px;
	padding:0;
	height:auto;
	vertical-align:top
}

.pressthis-bookmarklet span:before
{
	color:#777;
	font:400 20px/1 dashicons;
	content:'\f157';
	position:relative;
	display:inline-block;
	top:4px;
	margin-right:4px
}

.pressthis-js-toggle .dashicons
{
	margin:5px 8px 6px 7px;
	color:#777
}

#local-time,#utc-time
{
	padding-left:25px;
	font-style:italic
}

.defaultavatarpicker .avatar
{
	margin:2px 0;
	vertical-align:middle
}

.options-general-php input.small-text
{
	width:56px
}

.options-general-php .spinner
{
	float:none;
	margin:0 3px
}

.options-general-php .language-install-spinner,.settings-php .language-install-spinner
{
	display:inline-block;
	float:none;
	margin:-3px 5px 0;
	vertical-align:middle
}

.setup-php textarea
{
	max-width:100%
}

.form-field #site-address
{
	max-width:25em
}

.form-field #domain
{
	max-width:22em
}

.form-field #admin-email,.form-field #blog_last_updated,.form-field #blog_registered,.form-field #path,.form-field #site-title
{
	max-width:25em
}

.form-field #path
{
	margin-bottom:5px
}

#search-sites,#search-users
{
	max-width:100%
}

.request-filesystem-credentials-dialog
{
	display:none
}

.request-filesystem-credentials-dialog .notification-dialog
{
	top:15%;
	max-height:85%
}

.request-filesystem-credentials-dialog-content
{
	margin:25px
}

.request-filesystem-credentials-form input[type=text],.request-filesystem-credentials-form input[type=password]
{
	display:block
}

.request-filesystem-credentials-dialog input[type=text],.request-filesystem-credentials-dialog input[type=password]
{
	width:100%
}

.request-filesystem-credentials-form .field-title
{
	font-weight:600
}

.request-filesystem-credentials-dialog label[for=hostname],.request-filesystem-credentials-dialog label[for=public_key],.request-filesystem-credentials-dialog label[for=private_key]
{
	display:block;
	margin-bottom:1em
}

.request-filesystem-credentials-dialog .ftp-password,.request-filesystem-credentials-dialog .ftp-username
{
	float:left;
	width:48%
}

.request-filesystem-credentials-dialog .ftp-password
{
	margin-left:4%
}

.request-filesystem-credentials-dialog .request-filesystem-credentials-action-buttons
{
	text-align:right
}

#request-filesystem-credentials-dialog .button:not(:last-child),.request-filesystem-credentials-dialog label[for=ftp]
{
	margin-right:10px
}

#request-filesystem-credentials-form .cancel-button
{
	display:none
}

#request-filesystem-credentials-dialog .cancel-button
{
	display:inline
}

@media screen and (max-width:782px) {
	textarea
	{
		-webkit-appearance:none
	}
	
	input[type=text],input[type=search],input[type=password],input[type=email],input[type=number]
	{
		-webkit-appearance:none;
		padding:6px 10px
	}
	
	input.code
	{
		padding-bottom:5px;
		padding-top:10px
	}
	
	.widefat tfoot td input[type=checkbox],.widefat th input[type=checkbox],.widefat thead td input[type=checkbox],input[type=checkbox]
	{
		-webkit-appearance:none;
		padding:10px
	}
	
	.widefat tfoot td input[type=checkbox],.widefat th input[type=checkbox],.widefat thead td input[type=checkbox]
	{
		margin-bottom:8px
	}
	
	.widefat tfoot td input[type=checkbox]:before,.widefat th input[type=checkbox]:before,.widefat thead td input[type=checkbox]:before,input[type=checkbox]:checked:before
	{
		font:400 30px/1 Dashicons;
		margin:-3px -5px
	}
	
	input[type=radio],input[type=checkbox]
	{
		height:25px;
		width:25px
	}
	
	.hr2-admin p input[type=radio],.hr2-admin p input[type=checkbox]
	{
		margin-top:-3px
	}
	
	input[type=radio]:checked:before
	{
		vertical-align:middle;
		width:9px;
		height:9px;
		margin:7px;
		line-height:16px
	}
	
	.hr2-upload-form input[type=submit]
	{
		margin-top:10px
	}
	
	#hr2body select
	{
		height:36px;
		font-size:16px
	}
	
	.hr2-admin .button-cancel
	{
		padding:0;
		font-size:14px
	}
	
	#adduser .form-field input,#createuser .form-field input
	{
		width:100%
	}
	
	.form-table
	{
		-webkit-box-sizing:border-box;
		-moz-box-sizing:border-box;
		box-sizing:border-box
	}
	
	.form-table td,.form-table th
	{
		display:block;
		width:auto;
		vertical-align:middle
	}
	
	.form-table .color-palette td
	{
		display:table-cell;
		width:15px
	}
	
	.form-table table.color-palette
	{
		margin-right:10px
	}
	
	input,textarea
	{
		font-size:16px
	}
	
	#profile-page .form-table textarea,.form-table span.description,.form-table td input[type=text],.form-table td input[type=password],.form-table td input[type=email],.form-table td select,.form-table td textarea
	{
		width:100%;
		font-size:16px;
		line-height:1.5;
		padding:7px 10px;
		display:block;
		max-width:none;
		-webkit-box-sizing:border-box;
		-moz-box-sizing:border-box;
		box-sizing:border-box
	}
	
	.form-table .form-required.form-invalid td:after
	{
		float:right;
		margin:-30px 3px 0 0
	}
	
	#hr2body .form-table td select
	{
		height:40px
	}
	
	.form-table input[type=text].small-text,input[type=text].small-text,input[type=search].small-text,input[type=password].small-text,input[type=number].small-text
	{
		width:auto;
		max-width:55px;
		display:inline;
		padding:3px 6px;
		margin:0 3px
	}
	
	#pass-strength-result
	{
		width:100%;
		-webkit-box-sizing:border-box;
		-moz-box-sizing:border-box;
		box-sizing:border-box;
		padding:8px
	}
	
	p.search-box
	{
		float:none;
		position:absolute;
		bottom:0;
		width:98%;
		height:90px;
		margin-bottom:20px
	}
	
	p.search-box input[name="s"]
	{
		height:auto;
		float:none;
		width:100%;
		margin-bottom:10px;
		vertical-align:middle;
		-webkit-appearance:none
	}
	
	p.search-box input[type=submit]
	{
		margin-bottom:10px
	}
	
	.form-table span.description
	{
		display:inline;
		padding:4px 0 0;
		line-height:1.4em;
		font-size:14px
	}
	
	.form-table th
	{
		padding-top:10px;
		padding-bottom:0;
		border-bottom:0
	}
	
	.form-table td
	{
		margin-bottom:0;
		padding-bottom:6px;
		padding-top:4px;
		padding-left:0
	}
	
	.form-table.permalink-structure td code
	{
		margin-left:32px
	}
	
	.form-table.permalink-structure td input[type=text]
	{
		margin-left:32px;
		margin-top:4px;
		width:96%
	}
	
	.form-table input.regular-text
	{
		width:100%
	}
	
	.form-table label
	{
		font-size:14px
	}
	
	.form-table fieldset label
	{
		display:block
	}
	
	#utc-time
	{
		margin-top:10px
	}
	
	#local-time,#utc-time
	{
		display:block;
		float:none;
		padding:0;
		line-height:2
	}
	
	.form-field #domain
	{
		max-width:none
	}
	
	.hr2-pwd
	{
		position:relative
	}
	
	.hr2-pwd [type=text],.hr2-pwd [type=password]
	{
		padding-right:40px
	}
	
	.hr2-pwd button.button
	{
		background:0 0;
		border:none;
		-webkit-box-shadow:none;
		box-shadow:none;
		line-height:2;
		margin:0;
		padding:5px 10px;
		position:absolute;
		right:0;
		top:0
	}
	
	.hr2-pwd button.button:active,.hr2-pwd button.button:focus,.hr2-pwd button.button:hover
	{
		background:0 0
	}
	
	.hr2-pwd .button .text
	{
		display:none
	}
}

.locale-he-il em,.locale-zh-cn #local-time,.locale-zh-cn #utc-time,.locale-zh-cn .form-wrap p,.locale-zh-cn .howto,.locale-zh-cn .inline-edit-row fieldset span.checkbox-title,.locale-zh-cn .inline-edit-row fieldset span.title,.locale-zh-cn .js .input-with-default-title,.locale-zh-cn .link-to-original,.locale-zh-cn .tablenav .displaying-num,.locale-zh-cn p.description,.locale-zh-cn p.help,.locale-zh-cn p.install-help,.locale-zh-cn span.description
{
	font-style:normal
}

body,html
{
	background:#f1f1f1
}

@media only screen and (max-width:768px) {
	.form-field input[type=text],.form-field input[type=password],.form-field input[type=email],.form-field select,.form-field textarea
	{
		width:99%
	}
	
	.form-wrap .form-field
	{
		padding:0
	}
	
	#profile-page .form-table textarea
	{
		max-width:400px;
		width:auto
	}
}

.locale-de-de #customize-header-actions .button,.locale-de-de-formal #customize-header-actions .button,.locale-ru-ru #customize-header-actions .button
{
	padding:0 5px 1px
}

@media only screen and (max-height:480px) {
	.request-filesystem-credentials-dialog .notification-dialog
	{
		width:100%;
		height:100%;
		max-height:100%;
		position:fixed;
		top:0;
		margin:0;
		left:0
	}
}

@media screen and (max-width:600px) {
	.color-option
	{
		width:49%
	}
}

body.rtl,body.rtl .press-this a.hr2-switch-editor
{
	font-family:Tahoma,Arial,sans-serif
}

.rtl h1,.rtl h2,.rtl h3,.rtl h4,.rtl h5,.rtl h6
{
	font-family:Arial,sans-serif;
	font-weight:700
}

body.locale-he-il,body.locale-he-il .press-this a.hr2-switch-editor
{
	font-family:Arial,sans-serif
}

.locale-he-il em
{
	font-weight:700
}

.locale-zh-cn .hdnle a
{
	font-size:12px
}

.locale-zh-cn form.upgrade .hint
{
	font-style:normal;
	font-size:100%
}

.locale-zh-cn #sort-buttons
{
	font-size:1em!important
}

.locale-de-de #customize-header-actions .spinner,.locale-de-de-formal #customize-header-actions .spinner
{
	margin:16px 3px 0
}

.locale-ru-ru .inline-edit-row fieldset label span.title,.locale-ru-ru .inline-edit-row fieldset.inline-edit-date legend
{
	width:8em
}

.locale-ru-ru .inline-edit-row fieldset .timestamp-wrap,.locale-ru-ru .inline-edit-row fieldset label span.input-text-wrap
{
	margin-left:8em
}

.locale-ru-ru.post-new-php .tagsdiv .newtag,.locale-ru-ru.post-php .tagsdiv .newtag
{
	width:165px
}

.locale-ru-ru.press-this .posting
{
	margin-right:277px
}

.locale-ru-ru .press-this-sidebar
{
	width:265px
}

.locale-ru-ru #customize-header-actions .spinner
{
	margin:16px 3px 0
}

.locale-lt-lt .inline-edit-row fieldset label span.title,.locale-lt-lt .inline-edit-row fieldset.inline-edit-date legend
{
	width:8em
}

.locale-lt-lt .inline-edit-row fieldset .timestamp-wrap,.locale-lt-lt .inline-edit-row fieldset label span.input-text-wrap
{
	margin-left:8em
}

@media screen and (max-width:782px) {
	.locale-lt-lt .inline-edit-row fieldset .timestamp-wrap,.locale-lt-lt .inline-edit-row fieldset label span.input-text-wrap,.locale-ru-ru .inline-edit-row fieldset .timestamp-wrap,.locale-ru-ru .inline-edit-row fieldset label span.input-text-wrap
	{
		margin-left:0
	}
}

body,html
{
	height:100%;
	margin:0;
	padding:0
}

body
{
	min-width:0;
	color:#444;
	font-family:"Open Sans",sans-serif;
	font-size:13px;
	line-height:1.4em
}

a
{
	color:#0073aa;
	-webkit-transition-property:border,background,color;
	transition-property:border,background,color;
	-webkit-transition-duration:.05s;
	transition-duration:.05s;
	-webkit-transition-timing-function:ease-in-out;
	transition-timing-function:ease-in-out;
	outline:0
}

a:active,a:hover
{
	color:#00a0d2
}

a:focus
{
	color:#124964;
	-webkit-box-shadow:0 0 0 1px #5b9dd9,0 0 2px 1px rgba(30,140,190,.8);
	box-shadow:0 0 0 1px #5b9dd9,0 0 2px 1px rgba(30,140,190,.8)
}

.ie8 a:focus
{
	outline:#5b9dd9 solid 1px
}

p
{
	line-height:1.5
}

.login .message
{
	border-left:4px solid #00a0d2;
	background-color:#fff;
	-webkit-box-shadow:0 1px 1px 0 rgba(0,0,0,.1);
	box-shadow:0 1px 1px 0 rgba(0,0,0,.1)
}

.login #login_error
{
	border-left:4px solid #dd3d36;
	background:#fff;
	-webkit-box-shadow:0 1px 1px 0 rgba(0,0,0,.1);
	box-shadow:0 1px 1px 0 rgba(0,0,0,.1)
}

#loginform p.submit,.login-action-lostpassword p.submit
{
	border:none;
	margin:-10px 0 20px
}

.login *
{
	margin:0;
	padding:0
}

.login form
{
	margin-top:20px;
	margin-left:0;
	padding:26px 24px 46px;
	background:#fff;
	-webkit-box-shadow:0 1px 3px rgba(0,0,0,.13);
	box-shadow:0 1px 3px rgba(0,0,0,.13)
}

.login form .forgetmenot
{
	font-weight:400;
	float:left;
	margin-bottom:0
}

.login .button-primary
{
	float:right
}

#login form p
{
	margin-bottom:0
}

#login form p.submit
{
	margin:0;
	padding:0
}

.login label
{
	color:#777;
	font-size:14px
}

.login #backtoblog a,.login #nav a,.login h1 a
{
	text-decoration:none;
	color:#999
}

.login form .forgetmenot label
{
	font-size:12px;
	line-height:19px
}

.login h1 a
{
	-webkit-background-size:84px;
	background-size:84px;
	background-position:center top;
	background-repeat:no-repeat;
	height:84px;
	font-size:20px;
	line-height:1.3em;
	margin:0 auto 25px;
	padding:0;
	width:84px;
	text-indent:-9999px;
	outline:0;
	display:block
}

#login
{
	width:320px;
	padding:8% 0 0;
	margin:auto
}

#login_error,.login .message
{
	margin-left:0;
	padding:12px
}

.login #backtoblog,.login #nav
{
	font-size:13px;
	padding:0 24px
}

.login #nav
{
	margin:24px 0 0
}

#backtoblog
{
	margin:16px 0 0
}

.login #backtoblog a:hover,.login #nav a:hover,.login h1 a:hover
{
	color:#00a0d2
}

.login #backtoblog a:focus,.login #nav a:focus,.login h1 a:focus
{
	color:#124964
}

.login form .input,.login input[type=text]
{
	font-size:24px;
	width:100%;
	padding:3px;
	margin:2px 6px 16px 0
}

.login form .input,.login form input[type=checkbox],.login input[type=text]
{
	background:#fbfbfb
}

.ie7 .login form .input,.ie8 .login form .input
{
	font-family:sans-serif
}

.login-action-rp input[type=text]
{
	-webkit-box-shadow:none;
	box-shadow:none;
	margin:0
}

.login #pass-strength-result
{
	font-weight:600;
	margin:-1px 5px 16px 0;
	padding:6px 5px;
	text-align:center;
	width:100%
}

.mobile #login
{
	padding:20px 0
}

.mobile #login .message,.mobile #login form,.mobile #login_error
{
	margin-left:0
}

.mobile #login #backtoblog,.mobile #login #nav
{
	margin-left:8px
}

body.interim-login
{
	height:auto
}

.interim-login #login
{
	padding:0;
	margin:5px auto 20px
}

.interim-login.login h1 a
{
	width:auto
}

.interim-login #login_error,.interim-login.login .message
{
	margin:0 0 16px
}

.interim-login.login form
{
	margin:0
}

@media screen and (max-width:782px) {
	.interim-login input[type=checkbox]
	{
		height:16px;
		width:16px
	}
	
	.interim-login input[type=checkbox]:checked:before
	{
		width:16px;
		font:400 21px/1 dashicons;
		margin:-3px 0 0 -4px
	}
}

body.login
{
	background-color:#e9eff3;
	min-height:calc(100% + 100px)
}

body.login .message
{
	margin-top:24px;
	background:transparent;
	border:none;
	box-shadow:none
}

body.login #login_error
{
	margin-top:24px;
	box-shadow:none;
	background-color:transparent;
	padding:8px 24px
}

body.login-action-lostpassword #login
{
	max-width:480px;
	width:100%
}

.login h1
{
	background-color:#1e8cbe;
	border-bottom:1px solid #0079aa;
	position:absolute;
	top:0;
	right:0;
	left:0;
	height:46px;
	text-align:left
}

.login h1 a,.login h1 a:hover
{
	color:#fff;
	height:auto;
	width:auto;
	background:none;
	font-weight:300;
	font-size:18px;
	margin:0;
	overflow:auto;
	text-indent:0;
	display:inline-block;
	line-height:46px;
	padding:0 12px;
	letter-spacing:-.5px
}

.login h1 a:before
{
	content:'';
	display:inline-block;
	height:28px;
	width:28px;
	/*background-image: url("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAAQABADASIAAhEBAxEB/8QAFQABAQAAAAAAAAAAAAAAAAAAAgX/xAAiEAEAAgIBBAIDAAAAAAAAAAABAgMEERIFExQhADEiMmH/xAAUAQEAAAAAAAAAAAAAAAAAAAAF/8QAHhEAAgEDBQAAAAAAAAAAAAAAARESAAJhAwUUQZH/2gAMAwEAAhEDEQA/AJ1WfVgFrbfbi2WV8KsutsPHkpuUmAuk5Rf5JQUBeRdHIpxbDK82bVqeWWWSL0lI2dwJBECGkP02en5P61HHs6JlQlKuucYljzu/Ox5xjxhH6dbi8TTomq+gHRMrHv6FhUUh3cdl3JM3aMpocfQDy39u9R0x0i6SOYLkWlhUMNEnZiJCMpPtr3Ff/9k="); */
	background-position:left center;
	background-repeat:no-repeat;
	background-size:28px 28px;
	position:relative;
	top:6px;
	margin-right:8px
}

body.login.mobile #login
{
	max-width:100%
}

.login form
{
	background:#fff;
	border:1px solid #d4dfe7;
	box-shadow:0 1px 2px 0 #e7edf3;
	padding:20px 24px 24px;
	margin-top:40px;
	margin-left:auto;
	margin-right:auto
}

#login form p
{
	margin-bottom:16px
}

.login label
{
	font-size:14px;
	font-weight:600;
	margin-bottom:5px;
	color:#2e4453;
	line-height:24px
}

.login label br
{
	display:block;
	height:4px;
	content:''
}

.login input.input
{
	margin:0;
	padding:7px 14px;
	width:100%;
	color:#2e4453;
	font-size:16px;
	font-weight:400;
	line-height:1.5;
	border:1px solid #c8d7e1;
	background-color:#fff;
	-webkit-box-shadow:none;
	box-shadow:none;
	transition:all .15s ease-in-out
}

.login input.input:hover
{
	border-color:#a8bece
}

.login input.input:focus
{
	border-color:#0087be;
	outline:none;
	box-shadow:0 0 0 2px #78dcfa
}

.login p.forgetmenot label
{
	font-weight:400
}

.login p.forgetmenot input
{
	-webkit-box-shadow:none;
	box-shadow:none;
	border:1px solid #c8d7e1;
	background-color:#fff;
	transition:all .15s ease-in-out;
	box-sizing:border-box
}

.login p.forgetmenot input:hover
{
	border-color:#a8bece
}

.login p.forgetmenot input:focus
{
	border-color:#0087be;
	outline:none;
	box-shadow:0 0 0 2px #78dcfa
}

.login p.submit input#hr2-submit
{
	float:none;
	clear:both;
	width:100%;
	display:block;
	background:#00aadc;
	border-color:#0087be;
	color:#fff;
	border-style:solid;
	border-width:1px 1px 2px;
	cursor:pointer;
	margin:0;
	outline:0;
	overflow:hidden;
	font-weight:600;
	text-overflow:ellipsis;
	text-decoration:none;
	vertical-align:top;
	box-sizing:border-box;
	font-size:14px;
	line-height:1.3;
	height:auto;
	border-radius:4px;
	padding:10px 16px;
	-webkit-appearance:none;
	appearance:none
}

.login p.submit input#hr2-submit:hover
{
	border-color:#005082;
	color:#fff
}

.login p.submit input#hr2-submit:active
{
	border-width:2px 1px 1px
}

.login p.submit input#hr2-submit:focus
{
	border-color:#005082;
	box-shadow:0 0 0 2px #78dcfa
}

.login #nav
{
	color:transparent;
	padding:0;
	margin-top:1px
}

.login #nav a
{
	display:block;
	padding:16px 24px;
	color:#87a6bc;
	border-bottom:1px solid rgba(200,215,225,0.5)
}

.login #nav a:hover
{
	color:#00aadc
}

.login #nav a:nth-child( 2n )
{
	margin-top:-18px
}

.login #backtoblog
{
	padding:0;
	margin-top:0
}

.login #backtoblog a
{
	display:block;
	padding:16px 24px;
	color:#87a6bc
}

.login #backtoblog a:hover
{
	color:#00aadc
}

.liquid-container-315
{
	width:100%;
	float:left;
	margin-right:-315px
}

.liquid-content-315
{
	margin-right:315px
}

.liquid-sidebar-315
{
	width:315px;
	float:right
}

.liquid-side-315
{
	width:300px;
	margin:0 0 0 15px
}

@media screen and (max-width:768px) {
	.liquid-container-315
	{
		width:100%;
		float:none;
		margin-right:0
	}
	
	.liquid-content-315
	{
		margin-right:0
	}
	
	.liquid-sidebar-315
	{
		width:100%;
		float:none
	}
	
	.liquid-side-315
	{
		width:100%;
		margin:0
	}
	
	body.login .message,body.login #login_error
	{
		margin-top:48px
	}
}

#blavatardiv .submit,#avatardiv .submit
{
	padding:0
}

#avatardiv h3,#personal-options h3,.profile-side h3
{
	margin-top:0
}

table#general-top
{
	width:630px
}

table#general-bottom
{
	clear:both
}

#profpic,.profile-side-box
{
	margin-top:10px;
	padding:10px;
	background:#fff;
	border:none!important;
	-webkit-box-shadow:0 1px 3px rgba(0,0,0,0.13);
	box-shadow:0 1px 3px rgba(0,0,0,0.13)
}

#profpic
{
	text-align:center
}

#profpic img
{
	border:8px solid #fff
}

#profpic p
{
	text-align:left
}

#personal-options
{
	margin-right:300px;
	padding:1em 3em 0 0;
	border:1px solid #fff
}

#profile-page .form-table input[type=text]
{
	width:300px
}

#current-theme .current-premium-theme
{
	background:#cfdfe9;
	font-size:15px;
	font-weight:300;
	margin:20px 0;
	padding:15px 20px
}

#current-theme .current-premium-theme a
{
	font-weight:700;
	text-decoration:none
}

#availablethemes
{
	clear:both
}

.available-theme .action-links
{
	margin:0
}

.available-theme h3 .flag
{
	background:#000;
	-moz-border-radius:2px;
	border-radius:2px;
	color:#daf8cd;
	font-size:12px;
	font-weight:400;
	left:20px;
	padding:2px 8px;
	position:relative;
	top:-2px
}

.available-theme h3 .flag.premium
{
	background:#f0a000;
	color:#fff;
	border:1px solid #c87800
}

.available-theme h3 .flag.vip
{
	background:#f1831e;
	color:#fff
}

.available-theme h3 .highlight1,.available-theme .themedetaildiv .highlight1
{
	background:#fcfcbe
}

.subsubsub
{
	margin:0 0 8px
}

.tablenav.themes .tablenav-pages
{
	float:none;
	width:100%
}

.themes .tablenav-pages .displaying-info
{
	color:#777;
	display:block;
	float:left;
	font:italic 12px/30px Georgia,"Times New Roman","Bitstream Charter",Times,serif;
	width:75%
}

body.mp6 .themes .tablenav-pages .displaying-info
{
	font-family:"Open Sans",sans-serif
}

.themes .tablenav-pages .displaying-num
{
	display:block;
	float:right;
	margin-right:0;
	text-align:right;
	width:25%
}

#current-theme
{
	background:#eaf3fa;
	border:1px solid #cfdfe9;
	border-radius:3px;
	margin:1em 0 1.5em;
	min-height:227px;
	padding:20px;
	position:relative
}

body.mp6 #current-theme
{
	background:#fff;
	border:none;
	border-radius:0;
	-webkit-box-shadow:0 1px 3px rgba(0,0,0,0.1);
	box-shadow:0 1px 3px rgba(0,0,0,0.1)
}

#current-theme.has-screenshot
{
	padding-left:350px
}

#current-theme.premium-theme.has-screenshot
{
	min-height:250px
}

#current-theme img
{
	max-width:300px;
	width:auto
}

@media only screen and (max-width:1200px) {
	#current-theme img
	{
		margin-left:-330px
	}
}

#current-theme h3
{
	display:none
}

#current-theme h4
{
	font-size:24px;
	font-weight:700;
	margin-top:10px
}

#current-theme .theme-description
{
	color:#535353;
	font-size:13px;
	line-height:22px;
	margin-top:5px
}

#current-theme .theme-info,#current-theme .theme-info a
{
	color:#575757;
	font-size:14px;
	font-weight:300
}

#current-theme .theme-options a
{
	background:#fff;
	background-image:-moz-linear-gradient(#fff,#f1f1f1);
	background-image:-webkit-linear-gradient(#fff,#f1f1f1);
	background-image:linear-gradient(#fff,#eee);
	box-shadow:inset 0 -1px 0 #ddd,inset -1px 0 0 #ddd;
	border-radius:3px;
	color:#666;
	display:inline-block;
	font-size:13px;
	height:20px;
	margin:5px 10px 0 0;
	padding:9px 16px 3px;
	text-decoration:none
}

body.mp6 #current-theme .theme-options a
{
	background:#fafafa;
	background:#fafafa;
	background:-moz-linear-gradient(top,rgba(250,250,250,1) 0%,rgba(233,233,233,1) 100%);
	background:-webkit-gradient(linear,left top,left bottom,color-stop(0%,rgba(250,250,250,1)),color-stop(100%,rgba(233,233,233,1)));
	background:-webkit-linear-gradient(top,rgba(250,250,250,1) 0%,rgba(233,233,233,1) 100%);
	background:-o-linear-gradient(top,rgba(250,250,250,1) 0%,rgba(233,233,233,1) 100%);
	background:linear-gradient(to bottom,rgba(250,250,250,1) 0%,rgba(233,233,233,1) 100%);
	border:1px solid #adadad;
	color:#333;
	line-height:14px
}

#current-theme .theme-options a:hover
{
	color:#21759B
}

body.mp6 #current-theme .theme-options a:hover
{
	background:#f3f3f3;
	background-image:-webkit-gradient(linear,left top,left bottom,from(#fff),to(#f3f3f3));
	background-image:-webkit-linear-gradient(top,#fff,#f3f3f3);
	background-image:-moz-linear-gradient(top,#fff,#f3f3f3);
	background-image:-o-linear-gradient(top,#fff,#f3f3f3);
	background-image:linear-gradient(to bottom,#fff,#f3f3f3);
	border-color:#999
}

.no-customize-support #current-theme .hide-if-no-customize,.customize-support #current-theme .hide-if-customize
{
	display:none
}

#current-theme .theme-options li
{
	border:0;
	margin-right:0;
	padding-right:0
}

#current-theme .theme-options span,#current-theme .theme-options .sep,#current-theme .theme-options .current-theme-tags,#current-theme .theme-options .item-nav-menus,#current-theme .theme-options .item-mobile-options,#current-theme .theme-options .item-ipad,#current-theme .theme-options .item-custom-header,#current-theme .theme-options .item-custom-background
{
	display:none
}

#current-theme .current-premium-theme
{
	background:#2ea2cc;
	color:#fff;
	color:rgba(255,255,255,0.8);
	font-size:15px;
	font-weight:300;
	margin:60px 0 0 -330px;
	padding:15px 20px
}

#current-theme .current-premium-theme a
{
	color:#fff;
	font-weight:700;
	text-decoration:none
}

#current-theme .current-premium-theme a:hover
{
	text-decoration:underline
}

#current-theme .theme-options .load-customize,body.mp6 #current-theme .theme-options .load-customize
{
	background-color:#2ea2cc;
	background-image:-moz-linear-gradient(#1e8cbe,#0074a2);
	background-image:-webkit-linear-gradient(#1e8cbe,#0074a2);
	background-image:-ms-linear-gradient(#1e8cbe,#0074a2);
	background-image:-o-linear-gradient(#1e8cbe,#0074a2);
	background-image:linear-gradient(#1e8cbe,#0074a2);
	-moz-box-shadow:inset 0 1px 0 #1e8cbe,inset 0 2px 0 rgba(120,200,230,0.3);
	-webkit-box-shadow:inset 0 1px 0 #1e8cbe,inset 0 2px 0 rgba(120,200,230,0.3);
	-o-box-shadow:inset 0 1px 0 #1e8cbe,inset 0 2px 0 rgba(120,200,230,0.3);
	-ms-box-shadow:inset 0 1px 0 #1e8cbe,inset 0 2px 0 rgba(120,200,230,0.3);
	box-shadow:inset 0 1px 0 #1e8cbe,inset 0 2px 0 rgba(120,200,230,0.3);
	color:#fff;
	border:none
}

body.mp6 #current-theme .theme-options .load-customize
{
	border:1px solid #0074a2;
	-moz-box-shadow:inset 0 1px 0 rgba(120,200,230,0.3);
	-webkit-box-shadow:inset 0 1px 0 rgba(120,200,230,0.3);
	-o-box-shadow:inset 0 1px 0 rgba(120,200,230,0.3);
	-ms-box-shadow:inset 0 1px 0 rgba(120,200,230,0.3);
	box-shadow:inset 0 1px 0 rgba(120,200,230,0.3)
}

#current-theme .theme-options .load-customize:hover,body.mp6 #current-theme .theme-options .load-customize:hover
{
	color:#fff;
	text-decoration:underline;
	background-image:-moz-linear-gradient(#2ea2cc,#0074a2);
	background-image:-webkit-linear-gradient(#2ea2cc,#0074a2);
	background-image:-o-linear-gradient(#2ea2cc,#0074a2);
	background-image:-ms-linear-gradient(#2ea2cc,#0074a2);
	background-image:linear-gradient(#2ea2cc,#0074a2);
	-moz-box-shadow:inset 0 1px 0 #2ea2cc,inset 0 2px 0 rgba(120,200,230,0.5);
	-webkit-box-shadow:inset 0 1px 0 #2ea2cc,inset 0 2px 0 rgba(120,200,230,0.5);
	-o-box-shadow:inset 0 1px 0 #2ea2cc,inset 0 2px 0 rgba(120,200,230,0.5);
	-ms-box-shadow:inset 0 1px 0 #2ea2cc,inset 0 2px 0 rgba(120,200,230,0.5);
	box-shadow:inset 0 1px 0 #2ea2cc,inset 0 2px 0 rgba(120,200,230,0.5)
}

body.mp6 #current-theme .theme-options .load-customize:hover
{
	text-decoration:none;
	-moz-box-shadow:inset 0 1px 0 rgba(120,200,230,0.5);
	-webkit-box-shadow:inset 0 1px 0 rgba(120,200,230,0.5);
	-o-box-shadow:inset 0 1px 0 rgba(120,200,230,0.5);
	box-shadow:inset 0 1px 0 rgba(120,200,230,0.5)
}

#contextual-help-wrap h5
{
	padding-left:0!important
}

#new-feedback
{
	padding-top:14px
}

.supportcats
{
	list-style:none;
	clear:both;
	text-indent:0
}

.supportcats li
{
	display:block;
	float:left;
	min-width:14em;
	white-space:nowrap
}

.supportcats a
{
	text-transform:capitalize;
	cursor:pointer
}

#quicklookup a
{
	cursor:pointer
}

#quicklookup strong
{
	display:block;
	margin-top:1em
}

#quicklookup ul
{
	float:left;
	list-style-type:disc
}

#quicklookup .list
{
	clear:both;
	float:left;
	width:100%;
	margin-bottom:10px
}

#quicklookup .list ul
{
	min-width:14em
}

#quicklookup .list ul li
{
	margin-left:2em
}

#quicklookup p.submit
{
	margin:0;
	padding:0
}

#contextual-help-wrap input[type="text"],#contextual-help-wrap textarea
{
	border:1px solid #dfdfdf
}

#dashboard_stats .inside
{
	margin:10px 0 0!important
}

#dashboard_stats #stats-graph
{
	margin:0
}

#dashboard_stats a.button
{
	float:right;
	margin:5px 0 10px
}

.main-stats
{
	border-top:1px solid #dfdfdf;
	margin:0 -10px;
	padding:10px;
	background:#fcfcfc;
	-moz-box-shadow:inset 0 1px 0 #fff;
	-webkit-box-shadow:inset 0 1px 0 #fff;
	box-shadow:inset 0 1px 0 #fff;
	overflow:hidden
}

body.admin-color-classic .main-stats
{
	background:#fff;
	border-top:1px solid #d0dfe9
}

.main-stats #top-posts,.main-stats #top-search
{
	float:left;
	width:50%
}

#top-posts .stats-section-inner p
{
	white-space:nowrap;
	overflow:hidden
}

#top-posts .stats-section-inner p a
{
	overflow:hidden;
	text-overflow:ellipsis
}

#stats-info div#active
{
	border-top:1px solid #dfdfdf;
	margin:0 -10px;
	padding:10px 10px 0;
	-moz-box-shadow:inset 0 1px 0 #fff;
	-webkit-box-shadow:inset 0 1px 0 #fff;
	box-shadow:inset 0 1px 0 #fff;
	overflow:hidden
}

body.admin-color-classic #stats-info div#active
{
	border-top:1px solid #d0dfe9
}

#top-search p
{
	color:#999
}

#stats-info h4
{
	font-size:1em;
	margin:0 0 .5em!important
}

#stats-info p
{
	margin:0 0 .25em;
	color:#999
}

#stats-info p.widget-loading
{
	margin:1em 0 0;
	color:#333
}

#stats-info p a
{
	display:block
}

#stats-info p a.button
{
	display:inline
}

fieldset.options
{
	margin:1em auto;
	border:1px solid #dadada
}

body#media-upload
{
	padding-top:0
}

p.upload-filetypes,p.upload-quota,p.upload-flash-bypass,p.upload-html-bypass
{
	color:#999;
	font-size:.9em;
	max-width:90%
}

.upload-quota span
{
	display:block
}

#flash-upload-ui p.howto
{
	display:none
}

#content_media
{
	display:none
}

ul.short-code-list
{
	margin:0 1.5ex;
	padding:3px 0 0;
	list-style:none;
	text-indent:0
}

.tablenav a.checkforspam
{
	float:left
}

.ui-tabs-loading
{
	outline:none;
	background:url(../images/loading.gif) no-repeat 0 50%
}

#thumbnail_crop,label[for=thumbnail_crop]
{
	display:none
}

.widefat th.column-stats
{
	text-align:center
}

.column-stats
{
	width:5em
}

.column-stats a
{
	width:16px;
	margin:0 auto
}

.message
{
	line-height:1.75em
}

#change-permalinks
{
	display:none
}

p.custom-css-help
{
	float:right;
	border:1px solid #000;
	width:200px;
	margin:0 0 0 10px;
	padding:6px
}

textarea#safecss
{
	background:#f9f9f9;
	color:#444;
	font-family:Consolas,Monaco,Courier,monospace;
	font-size:12px;
	line-height:16px;
	outline:none;
	padding:16px
}

#hotdiv h4
{
	color:#999;
	line-height:1.4em;
	margin-top:-.2em
}

#hotdiv li
{
	margin-bottom:.75em;
	color:#333
}

#hotdiv ol
{
	margin:1em 0 1.5em 20px
}

#hotdiv .imglist
{
	list-style:none;
	margin:1em 0 1.5em 6px
}

.imglist img
{
	padding-right:6px;
	vertical-align:middle;
	background:#fff
}

.product-expirations
{
	margin-bottom:8px
}

.product-expirations td
{
	padding:0 8px 2px 0
}

p.post-post,li.post-post,.post-post-right-column p
{
	font-size:13px
}

.post-post a
{
	text-decoration:none
}

a.ppbtn
{
	font-size:13px;
	border-radius:3px;
	text-decoration:none;
	text-shadow:#fff 0 1px 0;
	background-color:#eee;
	padding:2px 8px;
	display:inline-block;
	margin:0 1px;
	width:auto
}

a.ppbtn:active
{
	background-color:#f2f2f2
}

p.post-post-meta,p.post-post-comments
{
	font-size:15px
}

.post-post-column
{
	width:450px;
	float:left;
	margin-top:10px;
	margin-right:30px
}

.getmorereaders
{
	border-top:1px solid #eee
}

.post-post-links
{
	font-size:15px;
	line-height:1.5em;
	padding-bottom:20px;
	border-bottom:1px solid #eee;
	margin-right:20px;
	margin-top:0;
	margin:0 0 12px;
	padding:2px 0 16px
}

.post-post-right-column
{
	width:250px;
	background:#fbfbfb;
	padding:5px 18px 15px
}

.post-post-right-column h3
{
	margin-bottom:0
}

.post-post-column ul
{
	list-style:disc;
	padding-left:15px
}

.post-post-column ul li.post-post
{
	color:#333;
	line-height:1.25em;
	padding:0 0 2px
}

#tos-warning
{
	background:#dd3d36;
	color:#fff;
	border-left:4px solid #fff
}

#tos-warning a
{
	color:#fff;
	text-decoration:underline
}

#hr2body #hr2body-content #dashboard-widgets.columns-1 .postbox-container
{
	width:100%
}

#hr2body #hr2body-content #dashboard-widgets.columns-2 .postbox-container
{
	width:49.5%
}

#hr2body #hr2body-content #dashboard-widgets.columns-3 .postbox-container
{
	width:33.5%
}

#hr2body #hr2body-content #dashboard-widgets.columns-4 .postbox-container
{
	width:25%
}

#hr2com-tip
{
	position:relative;
	border-left-color:#2EA2CC
}

#hr2com-tip a#hide-tip
{
	display:none;
	padding:12px 10px;
	position:absolute;
	top:0;
	right:0
}

#hr2com-tip.tip-email-verify strong
{
	margin-bottom:.5em;
	display:block
}

#icann_verification_container
{
	overflow:auto
}

#icann_verification_container #notice
{
	float:left;
	width:65%;
	margin-right:5%
}

#icann_verification_container #notice p#icann_instructions_lead
{
	border-top:1px solid #eee;
	padding-top:20px;
	margin-top:20px
}

#icann_verification_container #notice p.icann_instructions
{
	color:#666
}

#icann_verification_container #example
{
	width:30%;
	float:right;
	background-color:#f1f1f1;
	padding:20px
}

#icann_verification_container #example p
{
	margin-top:0;
	font-size:14px;
	color:#555
}

body.customize-support #menu-appearance a[href^="themes.php?page=custom-header"],body.customize-support #menu-appearance a[href^="themes.php?page=custom-background"]
{
	display:none
}

body.customize-support .theme-overlay .theme-actions a[href="themes.php?page=custom-header"],body.customize-support .theme-overlay .theme-actions a[href="themes.php?page=custom-background"]
{
	display:none
}
</style>
<script type="text/javascript">
  function setSubmitUrl(form) {
    var action = "loginprocess";
    var hash = self.document.location.hash;
    if (hash) {
      var redirectTo = unescape(hash.substring(1));
      action = action + "?redirectTo=" + redirectTo;
    }
    form.action = action;
    return true;
  }
</script>
</head>
<body class="login login-action-login">
	<div id="login">
		<h1>
			<a href="javascript:void(0);" title="HR Intranet" tabindex="-1">HR
				Intranet</a>
		</h1>

		<form name="loginform" id="loginform" action="loginprocess"
			method="post" onSubmit="return setSubmitUrl(this);">
			<p>
				<label for="username">Email or Username<br /> <input
					type="text" name="username" id="username" class="input" value=""
					size="16" /></label>
			</p>
			<p>
				<label for="password">Password<br /> <input type="password"
					name="password" id="password" class="input" value="" size="16" /></label>
			</p>
			<p class="forgetmenot">
				<label for="rememberme"><input name="rememberme"
					type="checkbox" id="rememberme" value="forever" 
					disabled /> Stay signed in</label>
			</p>
			<p class="submit">
				<input type="submit" name="hr2-submit" id="hr2-submit"
					class="button button-primary button-large" value="Log In" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</p>
		</form>

		<p id="nav">
			<a class="click-register"
				href="register">Register</a> | <a
				href="lostpassword"
				title="Password Lost and Found">Lost your password?</a>
		</p>

		<script type="text/javascript">
      function hr2_attempt_focus() {
        setTimeout(function() {
          try {
            d = document.getElementById('user_login');
            d.focus();
            d.select();js
          } catch (e) {
          }
        }, 200);
      }

      hr2_attempt_focus();
      if (typeof hr2Onload == 'function')
        hr2Onload();
    </script>

	</div>
</body>
</html>