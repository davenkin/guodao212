<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

<head>
    <title>国道212</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="resources/images/212-red.ico" >
    <link rel="apple-touch-icon" href="resources/images/favicon64x64.ico"/>
    <link rel="stylesheet" type="text/css" href="resources/css/main.css">
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="http://code.highcharts.com/modules/exporting.js"></script>
    <script type="text/javascript" src="resources/js/main.js"></script>
</head>

<body>
<div id="top-wrapper">
    <div id="header">
        <a class="links" href="http://www.davenkin.me" target="_blank">无知者云</a>
        <h2>国道212</h2>
    </div>

    <div id="nav">
        <ul id="hul">
            <li><a id="rank-all" href="">BookRank</a>
                <ul>
                    <li><a id="amazon-iddd" href="">亚马逊-IDDD</a></li>
                    <li><a id="amazon-all" href="">亚马逊-全部</a></li>
                    <li><a id="jd-iddd" href="">京东-IDDD</a></li>
                    <li><a id="jd-all" href="">京东-全部</a></li>
                    <li><a id="dangdang-iddd" href="">当当-IDDD</a></li>
                    <li><a id="dangdang-all" href="">当当-全部</a></li>
                </ul>
            </li>
            <li><a href="http://www.w3schools.com/css/css3_intro.asp">CSS3</a>
                <ul>
                    <li><a href="http://tutorialzine.com/2013/10/12-awesome-css3-features-you-can-finally-use/">CSS3 New Features</a></li>
                    <li><a href="https://developer.mozilla.org/en-US/docs/Web/Guide/CSS/Media_queries">CSS3 Media Queries</a></li>
                    <li><a href="https://developer.mozilla.org/en-US/docs/Web/Guide/CSS/Using_CSS_animations">CSS3 Animation</a></li>
                </ul>
            </li>
            <li><a href="http://www.w3schools.com/js/">Javascirpt</a>
                <ul>
                    <li><a href="http://www.tutorialspoint.com/javascript/">JS Tutorial Point</a></li>
                    <li><a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Details_of_the_Object_Model">JS Object Model</a></li>
                    <li><a href="https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model/Introduction">JS DOM Basics</a></li>
                    <li><a href="http://code.tutsplus.com/tutorials/javascript-and-the-dom-series-lesson-1--net-3134">JS DOM Tutorial</a></li>
                    <li><a href="https://developer.mozilla.org/en-US/docs/AJAX/Getting_Started">JS Ajax</a></li>
                </ul>
            </li>
            <li><a href="http://www.w3schools.com/angular/default.asp">AngularJS</a>
                <ul>
                    <li><a href="https://docs.angularjs.org/tutorial/step_00">AngularJS Tutorial 1</a></li>
                    <li><a href="http://tutorials.jenkov.com/angularjs/index.html">AngularJS Tutorial 2</a></li>
                    <li><a href="http://www.angularjstutorial.com/">AngularJS Tutorial 3</a></li>
                    <li><a href="http://www.toptal.com/angular-js/a-step-by-step-guide-to-your-first-angularjs-app">AngularJS Tutorial 4</a></li>
                    <li><a href="https://thinkster.io/angulartutorial/mean-stack-tutorial/">AngularJS Tutorial 5</a></li>
                </ul>
            </li>
            <li><a href="http://newrelic.com/devops/toolset">DevOps</a>
                <ul>
                    <li><a href="https://docs.vagrantup.com/v2/getting-started/">Vagrant Tutorial</a></li>
                    <li><a href="http://www.pindi.us/blog/getting-started-puppet">Puppet Tutorial</a></li>
                    <li><a href="http://gettingstartedwithchef.com/">Chef Tutorial</a></li>
                    <li><a href="http://docs.ansible.com/intro_getting_started.html">Ansible Tutorial</a></li>
                    <li><a href="https://www.docker.com/">Docker</a></li>
                    <li><a href="http://developerblog.redhat.com/2014/05/15/practical-introduction-to-docker-containers/">Docker Tutorial 1</a></li>
                    <li><a href="http://blog.flux7.com/blogs/docker/docker-tutorial-series-part-1-an-introduction">Docker Tutorial 2</a></li>

                </ul>
            </li>
            <li><a href="http://nodejs.org/">Node.js</a>
                <ul>
                    <li><a href="http://www.nodebeginner.org/">Node.js Tutorial 1</a></li>
                    <li><a href="http://www.toptal.com/nodejs/why-the-hell-would-i-use-node-js">Node.js Tutorial 2</a></li>
                    <li><a href="http://amirrajan.net/nodejs-by-example/">Node.js Example</a></li>
                </ul>
            </li>

            <li><a href="http://nosql-database.org/">NoSQL</a>
                <ul>
                    <li><a href="http://openmymind.net/2011/11/8/Redis-Zero-To-Master-In-30-Minutes-Part-1/">Redis</a></li>
                    <li><a href="http://www.tutorialspoint.com/mongodb/">MongoDB Tutorial</a></li>
                    <li><a href="http://docs.neo4j.org/chunked/stable/preface.html">Neo4j Tutorial</a></li>
                </ul>
            </li>
            <li><a href="http://www.comtechies.com/2013/02/cloud-computing-tutorials-for-beginers.html">Cloud</a>
                <ul>
                    <li><a href="https://www.udemy.com/amazon-web-services-for-web-hosting-cloud-computing/">AWS Video</a></li>
                    <li><a href="http://aws.amazon.com/documentation/">AWS Documentation</a></li>
                    <li><a href="http://www.openstack.org/software/start/">OpenStack Tutorial</a></li>
                    <li><a href="http://www.tomsitpro.com/articles/paas-providers,1-1517.html">PaaS Providers</a></li>
                </ul>
            </li>
            <li><a href="http://www.linux.org/">Linux</a>
                <ul>
                    <li><a href="http://www.tutorialspoint.com/listtutorials/linux/1">Linux Tutorial</a></li>
                    <li><a href="https://www.linode.com/docs/tools-reference/linux-system-administration-basics">Linux Admin Basics</a></li>
                    <li><a href="http://www.ubuntu.com/">Ubuntu</a></li>
                    <li><a href="http://www.centos.org/">CentOS</a></li>
                </ul>
            </li>
            <li><a href="http://searchstorage.techtarget.com/guides/Big-data-tutorial-Everything-you-need-to-know">BigData</a>
                <ul>
                    <li><a href="https://developer.yahoo.com/hadoop/tutorial/">Hadoop Tutorial</a></li>
                    <li><a href="http://aisel.aisnet.org/cgi/viewcontent.cgi?article=3785&context=cais">Big Data Concepts</a></li>
                </ul>
            </li>
            <li><a href="http://getbootstrap.com/">Bootstrap</a>
                <ul>
                    <li><a href="http://www.tutorialspoint.com/bootstrap/">Bootstrap Tutorial Point</a></li>
                    <li><a href="http://www.w3schools.com/bootstrap/default.asp">Bootstrap W3schools</a></li>
                </ul>
            </li>
            <li><a href="https://developer.mozilla.org/en-US/docs/Web/Guide/Mobile">Mobile</a>
                <ul>
                    <li><a href="https://developer.mozilla.org/en-US/docs/Web_Development/Responsive_Web_design">Responsive Design</a></li>
                    <li><a href="http://developer.android.com/guide/components/fundamentals.html">Android Fundamentals</a></li>
                    <li><a href="http://developer.android.com/training/index.html">Android Tutorial 1</a></li>
                    <li><a href="http://www.tutorialspoint.com/android/">Android Tutorial 2</a></li>
                    <li><a href="http://www.smashingmagazine.com/guidelines-for-mobile-web-development/">Mobile Web Guidelines</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>

<div id="main-content-wrapper">
    <div id="side-bar">
    </div>
    <div id="main-content">
    </div>

</div>

<div id="wait"><img src='resources/images/wait2.gif' width="200px" height="200px"/></div>


</body>
</html>
