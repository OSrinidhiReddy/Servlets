$(function(){

	$.validator.addMethod('strongPassword',function(value,element){
		return this.optional(element)
		||value.length>=6 && /\d/.test(value) && /[a-z]/i.test(value);
	},'your passowrd should contain atleast 6 characters long and contains atleast 1 number and 1 char\'.')

	$("#redistrations").validate(
			{
				rules:{
					firstname:{
						required: true,
						strongPassword: true,
						nowhitespace:true
					},
					lastname:{
						required: true,
						strongPassword:true,
						nowhitespace:true
					},
					email:{
						required: true,
						email:true

					},
					password:{
						required:true,
						strongPassword:true
					},
					confirmpassword:{
						required:true,
						equalTo:"#password"
					},
				},
				messages:{
					firstname:{
						required:"plzz enter the firstname"
					},
					lastname:{
						required:"plzz enter the firstname"
					},
					email:{
						required:'plz enter only your valid email id...',

						email:'plzzzz eneter<em>valid</em> email address.'
					},
				},

			});
});
