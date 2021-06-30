import React, { useRef } from 'react';
import S3 from "react-aws-s3";

const test = () => {
  const fileInput = useRef();
    const handleClick = event => {
        event.preventDefault();
        let file = fileInput.current.files[0];
        let newFileName = fileInput.current.files[0].name;
        const config = {
            bucketName: process.env.REACT_test_S3_BUCKET_NAME,
            region: process.env.REACT_APP_S3_REGION,
            accessKeyId: process.env.REACT_APP_S3_ACCESS_ID,
            secretAccessKey: process.env.REACT_APP_S3_ACCESS_KEY,
            AllowedHeaders: [
                "*"
            ],
            AllowedMethods: [
                "HEAD",
                "GET",
                "PUT",
                "POST",
                "DELETE"
            ],
            AllowedOrigins: [
                "http://localhot:3000"
            ],
            ExposeHeaders: [
                 "ETag",
                 "x-amz-meta-custom-header"]
        }
        const Client = new S3(config);
        Client.uploadFile(file, newFileName).then(data => {
            if(data.status === 204){
                console.log("success");
                console.log(data.location);
            }else{
                console.log("fail to upload");
            }
        });
    }
    return (
            <form className='upload-steps' onSubmit={handleClick}>
                <label>
                    select your file:
                    <input type='file' ref={fileInput} />
                </label>
                <br/>
                <button type='submit'>Upload File</button>
            </form>
    )
}

export default test;