import React, { useState } from 'react'
import axios from 'axios'
const AudioUploader = () => {

    const[file,setFile] = useState(null);
    const [data,newData] = useState("");
    const[transcription,setTranscription] = useState("");
    const handleFileChange = (e)=>{
        setFile(e.target.files[0]);

    }

    const HandleUpload = async()=>{
        const formData  = new FormData();
        formData.append('file', file);
        try{
          const response = await axios.post('http://localhost:8080/api/transcribe',formData,{

            headers : {
                'Content-Type':'multipart/form-data'

            }
        });
        setTranscription(response.data);
        }catch(err){
            console.log(err)
        }
    };
    const handleCheck= async()=>{
        try{
            const response = await axios.get("http://localhost:8080/api/transcribe/ans",{
            headers:{
                'Content-Type':"String"
            }
        })
        newData(response.data);
        }catch(err){
            console.log(err)
        }
        
    }

  return (
    <div className='container'>
      <h1>Audio to Test Transcriber</h1>
      <div className='file-input'>
        <input onChange={handleFileChange} type="file" accept='audio/*' />
      </div>
      <button onClick={handleCheck}>Check running file</button>
      <p>{data}</p>
      <button className='upload-button' onClick={HandleUpload} >Upload and Transcribe</button>
      <div className='transcription-result'>
        <h2>Transcription Result</h2>
      </div>
    </div>
  )
}

export default AudioUploader
