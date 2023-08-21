import './Footer.css'

export default function Footer() {

    const myName = "Alexander Leonidas Guenzel"
    const company = "www.neuefische.de"
    const companyLink = "https://www.neuefische.de/en";
    const year = new Date().getFullYear();
    const course = "Java Fullstack Bootcamp";

  return (
    <>
        <div className="footer">
            <div className="footer-box">
                <p>
                    <span className="orange"> ©{year} </span>
                    <span className="yellow"> | </span>
                    <span> {myName.toUpperCase()} </span>
                    <span className="yellow"> | </span>
                    <span className="new-line"><br></br></span>
                    <a className="orange" href={companyLink}>{company.toUpperCase()}</a>
                    <span className="yellow"> | </span>
                    <span> {course.toUpperCase()} </span>
                </p>
            </div>
        </div >
    </>
  )
}

