import './Footer.css'

export default function Footer() {

    const myName = "Alexander Leonidas Guenzel"
    const company = "Neue Fische GmbH"
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
                    <span className="orange">{company.toUpperCase()}</span>
                    <span className="yellow"> | </span>
                    <span> {course.toUpperCase()} </span>
                </p>
            </div>
        </div >
    </>
  )
}

