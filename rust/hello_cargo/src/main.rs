use std::collections::HashMap;


fn main() -> Result<(), Box<dyn std::error::Error>> {
    let resp = reqwest::blocking::get("https://httpbin.org/ip")?
        .json::<HashMap<String, String>>()?;
    println!("{:#?}", resp);
    
    bla();

    return Ok(());
}

fn bla () {
    
    let result = reqwest::blocking::get("https://httpbin.org/ip");
    let response = result.unwrap();

    println!("status: {}", response.status());
    println!("headers: {:#?}", response.headers());
}