/*
@name:		gaDownload.js

@version:	1.0

*/


	
		function callGADownload(downloadURL) {
			const splitStr = downloadURL.split("?");
			const params = splitStr[1].split("&");
			var docId = params[0].split("=")[1];
			var docType = params[1].split("=")[1];
			var docName = params[2].split("=")[1];
			if(docType){
				docType=docType.replaceAll('+', ' ');
			}
			window.dataLayer.push({
				"event" : $('#event').val(),
				"documentId" : docId,
				"documentName" : docName,
				"documentType" : docType
			})
		}
