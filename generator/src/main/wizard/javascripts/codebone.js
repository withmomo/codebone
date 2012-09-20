/**
 * AutoCode JavaScript, version 1.0.0
 *
 *	@ version			:	1.0.0
 *	@ author			:	realbeast ( realbeast@entaz.com )
 *	@ last modified	:	2008-11-16
*/
var AutoCode = Class.create();

AutoCode.prototype = 
{
	Version: '1.0.0',

	initialize: function()
	{
	},
		
	checkSelectBoxStyle: function(nArrSize)
	{
		var desc;
		for( var i = 1 ; i <= nArrSize-1 ; i++)
		{
			desc = document.getElementById('fieldDesc'+i).value;

			if( desc == null )
				continue;

			this.checkSelectBox( 'selectBox_'+i, desc, i );
		}
	},
	
	checkSelectBox: function(id,tag,i)
	{
		var nStartIndex = tag.indexOf("{");
		var nEndIndex = tag.indexOf("}");
		
		if( ! (nStartIndex > -1 && nEndIndex > -1) )
		{
			return;
		}

		var div = document.getElementById(id);
		div.innerHTML = this.makeSelectBox(tag,i);
	},

	makeSelectBox: function(tag,i)
	{
		var index = i;
		var nStartIndex = tag.indexOf("{");
		var nEndIndex = tag.indexOf("}");

		if( ! (nStartIndex > -1 && nEndIndex > -1) && (nStartIndex > 0 && nEndIndex > 2)  )
		{
			alert('Description {name=value, name=value, name=value}\r\n형식으로 입력하지 않으셨습니다.\r\n확인해주세요.');
			return;
		}
		
		var title = tag.substring(0,nStartIndex);
		tag = tag.substring(nStartIndex+1,nEndIndex);
		
		var objects =	tag.split(",");
		var object;

		var keys	 = new Array();
		var values  = new Array();
		
		for( var i = 0 ; i < objects.length ; i++)
		{
			object	= objects[i];
			object	= object.split('=');
			
			if( object.length != 2 )
			{
				alert( '입력값 : ' + objects[i] + '\r\n\r\n name=value 형태가 아닙니다.\r\n확인해주세요.' );
				return;
			}
			keys[i] = object[0];
			values[i] = object[1];
		}
		
		tag = title + '&nbsp;<select>\r\n';

		for( var i = 0 ; i < keys.length ; i++)
		{
			tag = tag + '<option value="'+keys[i]+'">'+values[i]+'</option>\r\n';
		}
		tag = tag + '</select>\r\n';
		
		this.setSelectInputBox(index,keys,values);

		return tag;
	},

	setSelectInputBox: function(i,keys,values)
	{
		var keyElement = document.getElementById('fieldKey'+i);
		var valueElement = document.getElementById('fieldValue'+i);

		keyElement.value = keys;
		valueElement.value = values;
	}
};

// Instance
//
var _autoCode = new AutoCode();

function getParams() {
	var params = new Array();
	var uri = decodeURIComponent(location.href);
	uri = decodeURIComponent(uri);
	var splitsParams = uri.substring( uri.indexOf('?')+1, uri.length );
	splitsParams = splitsParams.split("&");
	var size = splitsParams.length;
	var key, value;
	for(var i = 0 ; i < size ; i++) {
		key = splitsParams[i].split("=")[0];
		value = splitsParams[i].split("=")[1];
		params[key] = value;
	}
	return params;
}

var request = getParams();